package dev.sha256.mightypets.Pets;

import dev.sha256.mightypets.MightyPets;
import dev.sha256.mightypets.Utils.ItemUtils;
import dev.sha256.mightypets.Utils.LevelSystem;
import dev.sha256.mightypets.Utils.VariableSystem;
import dev.sha256.mightypets.instance.ILevel;
import dev.sha256.mightypets.instance.Pet;
import net.brcdev.shopgui.event.ShopPostTransactionEvent;
import net.brcdev.shopgui.event.ShopPreTransactionEvent;
import net.md_5.bungee.api.chat.ClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class InterestPet extends Pet implements ILevel {

    private int level, xp, xpNeeded;

    private ItemStack item;


    @Override
    public String getID() {
        return "interest";
    }

    @Override
    public String getName() {
        return "InterestPet";
    }

    @Override
    public String[] getData() {
        return new String[] {
                getName(),
                String.valueOf(level),
                String.valueOf(this.xp),
                LevelSystem.getProgressBar(xp, xpNeeded, 20, '|', ChatColor.GREEN, ChatColor.RED)
        };
    }


    public InterestPet(MightyPets pets) {
        super(pets);

        YamlConfiguration config = pets.getPetConfig().getConfiguration();

        this.item = ItemUtils.skullFromName(config.getString("Pets." + getID() + ".item.material"), config.getString("Pets." + getID() + ".item.owner"), config.getInt("Pets." + getID() + ".item.durability"), VariableSystem.applyAllVariables(config.getString("Pets." + getID() + ".item.name"), getData()), VariableSystem.applyAllVariablesToList(config.getStringList("Pets." + getID() + ".item.lore"), getData()));
    }


    @Override
    public void levelup() {
        if(xp >= xpNeeded) {
            this.level += 1;

            this.setNBTData("level", this.level);
        }
    }


    @Override
    public Integer calculateXpNeeded(String equation) {
        return Integer.parseInt(VariableSystem.solveEquation(VariableSystem.convertToInt(equation, "level", level)));
    }

    @Override
    public void reset() {

    }



    @EventHandler
    public void onPurchase(ShopPostTransactionEvent event) {
        Player player = event.getResult().getPlayer();

        if(player.getInventory().contains(getItem())) {
            player.sendMessage("Test");
        }
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        Action action = event.getAction();

       if(player.getInventory().contains(item)) {
           player.sendMessage("Test");
       }
    }
}
