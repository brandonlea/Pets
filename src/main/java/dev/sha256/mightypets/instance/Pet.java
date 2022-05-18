package dev.sha256.mightypets.instance;

import de.tr7zw.nbtapi.NBTItem;
import dev.sha256.mightypets.MightyPets;
import dev.sha256.mightypets.Utils.ItemUtils;
import dev.sha256.mightypets.Utils.LevelSystem;
import dev.sha256.mightypets.Utils.VariableSystem;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class Pet {

    public abstract String getID();

    public abstract String getName();

    public abstract String[] getData();


    private NBTItem nbtItem;

    private ItemStack item;


    public void init(YamlConfiguration config) {

        this.item = ItemUtils.skullFromName(config.getString("Pets." + getID() + ".item.material"), config.getString("Pets." + getID() + ".item.owner"), config.getInt("Pets." + getID() + ".item.durability"), VariableSystem.applyAllVariables(config.getString("Pets." + getID() + ".item.name"), getData()), VariableSystem.applyAllVariablesToList(config.getStringList("Pets." + getID() + ".item.lore"), getData()));

    }

    public MightyPets getMightyPets() {
        return MightyPets.getPlugin();
    }


    public void setNBTData(String key, Object value) {
        nbtItem = new NBTItem(item);
        nbtItem.setObject(key, value);
        nbtItem.applyNBT(item);
    }

    public boolean checkID(String id) {
        return getID().equalsIgnoreCase(id);
    }
}
