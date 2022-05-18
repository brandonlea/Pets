package dev.sha256.mightypets.Utils;

import de.tr7zw.nbtapi.NBTItem;
import dev.sha256.mightypets.MightyPets;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ItemUtils {

    public static ItemStack skullFromName(String material, String owner, int durability, String name, List<String> lore) {
        ItemStack item = new ItemStack(Material.matchMaterial(material));
        item.setDurability((short) durability);

        SkullMeta meta = (SkullMeta) item.getItemMeta();

        meta.setOwner(owner);

        List<String> clore = new ArrayList<>();

        for(String l : lore) {
            clore.add(ChatColor.translateAlternateColorCodes('&', l));
        }

        meta.setLore(clore);
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));

        item.setItemMeta(meta);


        return item;
    }

    public static ItemStack UpdateLoreAndName(ItemStack item, String name, List<String> lore, MightyPets pets) {
        NBTItem nbtItem = new NBTItem(item);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',
                VariableSystem.convertToString(VariableSystem.convertToInt(name, "{level}", nbtItem.getInteger("level")), "{name}",
                        pets.getPetConfig().getConfiguration().getString("Pets." + nbtItem.getString("id") + ".name"))));


        int xpNeeded = LevelSystem.calculateXPNextLevel(pets.getPetConfig().getConfiguration().getString("Pets." + nbtItem.getString("id") + ".experience-equation"), nbtItem.getInteger("level"));

        System.out.println(xpNeeded);

        String[] data = {
            meta.getDisplayName(),
                String.valueOf(nbtItem.getInteger("level")),
                String.valueOf(nbtItem.getInteger("xp")),
                LevelSystem.getProgressBar(nbtItem.getInteger("xp"), xpNeeded, 20, '|', ChatColor.GREEN, ChatColor.RED)
        };

        List<String> newLore = VariableSystem.applyAllVariablesToList(lore, data);

        List<String> clore = new ArrayList<>();


        for(String l : newLore) {
            clore.add(ChatColor.translateAlternateColorCodes('&', l));
        }

        meta.setLore(clore);

        item.setItemMeta(meta);

        return item;
    }


}
