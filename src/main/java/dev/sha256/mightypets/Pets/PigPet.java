package dev.sha256.mightypets.Pets;

import de.tr7zw.nbtapi.NBTItem;
import dev.sha256.mightypets.MightyPets;
import dev.sha256.mightypets.Utils.ItemUtils;
import dev.sha256.mightypets.Utils.LevelSystem;
import dev.sha256.mightypets.Utils.VariableSystem;
import dev.sha256.mightypets.instance.IPet;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PigPet implements IPet, Listener {

    //Remove this class and remake using less code
    //Maybe making IPet into Abstract class


    private final String id = "pig";

    private String name;

    private Integer level, maxLevel, xp;

    private ItemStack item;

    private YamlConfiguration configuration;

    private MightyPets pets;

    private NBTItem nbtItem;

    private Boolean isActive = true;

    public PigPet(MightyPets pets) {
        this.pets = pets;
        this.level = 1;
        this.xp = 0;
    }

    @Override
    public void register()  {

        this.name = pets.getPetConfig().getConfiguration().getString("Pets." + id + ".name");


        String[] data = {
                this.name,
                String.valueOf(this.level),
                String.valueOf(this.xp),
                LevelSystem.getProgressBar(xp, LevelSystem.calculateXPNextLevel(pets.getPetConfig().getConfiguration().getString("Pets." + id + ".experience-equation"), this.level), 20, '|', ChatColor.GREEN, ChatColor.RED)
        };



        this.item = ItemUtils.skullFromName(
                pets.getPetConfig().getConfiguration().getString("Pets." + id + ".item.material"),
                pets.getPetConfig().getConfiguration().getString("Pets." + id + ".item.owner"),
                pets.getPetConfig().getConfiguration().getInt("Pets." + id + ".item.durability"),
                VariableSystem.applyAllVariables(pets.getPetConfig().getConfiguration().getString("Pets." + id + ".item.name"), data),
                VariableSystem.applyAllVariablesToList(pets.getPetConfig().getConfiguration().getStringList("Pets." + id + ".item.lore"), data));


    }

    @Override
    public void givePlayer(Player player) {
        nbtItem = new NBTItem(item);

        nbtItem.setString("id", id);
        nbtItem.setInteger("level", this.level);
        nbtItem.setInteger("xp", this.xp);
        nbtItem.setBoolean("enabled", this.isActive);
        nbtItem.applyNBT(item);


        player.getInventory().addItem(nbtItem.getItem());
    }

    @Override
    public boolean checkID(String id) {
        return this.id.equalsIgnoreCase(id);
    }

    @Override
    public void giveXp(int xp, Player player) {
        nbtItem = new NBTItem(player.getInventory().getItemInMainHand());
        nbtItem.setInteger("xp", nbtItem.getInteger("xp") + xp);
        nbtItem.applyNBT(player.getInventory().getItemInMainHand());
        player.sendMessage("XP: " + nbtItem.getInteger("xp"));


        int levels = LevelSystem.calculateLevelByXp(xp, pets.getPetConfig().getConfiguration().getString("Pets." +
                nbtItem.getString("id") + ".experience-equation"), nbtItem.getInteger("level"), pets.getPetConfig().getConfiguration().getInt("Pets." +
                nbtItem.getString("id") + ".max-level"));


        nbtItem.setInteger("level", levels);
        nbtItem.applyNBT(player.getInventory().getItemInMainHand());

        ItemUtils.UpdateLoreAndName(player.getInventory().getItemInMainHand(), pets.getPetConfig().getConfiguration().getString("Pets." + id + ".item.name"), pets.getPetConfig().getConfiguration().getStringList("Pets." + id + ".item.lore"), pets);
    }

    @EventHandler
    public void onHungerChange(FoodLevelChangeEvent event) {
       if(event.getEntity().getInventory().contains(item)) {
           if(isActive) event.setCancelled(true);
       }
    }

    @EventHandler
    @Override
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        nbtItem = new NBTItem(player.getInventory().getItemInMainHand());

        if(event.getItem().getItemMeta().getDisplayName().equals(item.getItemMeta().getDisplayName())) {
            setActive(!nbtItem.getBoolean("enabled"));
            nbtItem.setBoolean("enabled", isActive);
            nbtItem.applyNBT(player.getInventory().getItemInMainHand());
            player.sendMessage("You're pet is now " + (isActive ? "enabled" : "disabled"));
        }
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public void setItem(ItemStack item) {
        this.item = item;
    }

}