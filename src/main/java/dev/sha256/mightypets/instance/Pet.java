package dev.sha256.mightypets.instance;

import de.tr7zw.nbtapi.NBTItem;
import dev.sha256.mightypets.MightyPets;
import dev.sha256.mightypets.Utils.ItemUtils;
import dev.sha256.mightypets.Utils.LevelSystem;
import dev.sha256.mightypets.Utils.VariableSystem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public abstract class Pet implements Listener {

    public abstract String getID();

    public abstract String getName();

    public abstract String[] getData();



    private NBTItem nbtItem;

    private final ItemStack item;

    private YamlConfiguration config;


    public Pet(MightyPets pets) {
        this.config = pets.getPetConfig().getConfiguration();


        this.item = ItemUtils.skullFromName(config.getString("Pets." + getID() + ".item.material"), config.getString("Pets." + getID() + ".item.owner"), config.getInt("Pets." + getID() + ".item.durability"), VariableSystem.applyAllVariables(config.getString("Pets." + getID() + ".item.name"), getData()), VariableSystem.applyAllVariablesToList(config.getStringList("Pets." + getID() + ".item.lore"), getData()));
    }



    public void setNBTData(String key, Object value) {
        nbtItem = new NBTItem(item);
        nbtItem.setObject(key, value);
        nbtItem.applyNBT(item);
    }

    public void givePlayer(Player player) {
        player.getInventory().setItemInMainHand(this.item);
    }

    public boolean checkInventoryForPet(Player player) {
        return player.getInventory().contains(this.item);
    }

    public boolean checkID(String id) {
        return getID().equalsIgnoreCase(id);
    }


    public NBTItem getNbtItem() {
        return nbtItem;
    }

    public ItemStack getItem() {
        return item;
    }


    public MightyPets getMightyPets() {
        return MightyPets.getPlugin();
    }

}
