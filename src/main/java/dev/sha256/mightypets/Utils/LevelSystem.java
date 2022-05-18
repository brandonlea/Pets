package dev.sha256.mightypets.Utils;

import com.google.common.base.Strings;
import de.tr7zw.nbtapi.NBTItem;
import dev.sha256.mightypets.instance.Pet;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class LevelSystem {


    private Pet pet;

    public LevelSystem(Pet pet) {
        this.pet = pet;

    }

    public void levelup() {
    }

    //Write a Level System that takes in IPet so it can update NBT Data once leveled up.
    //Once leveled up set new XpNeeded for next level
    //Reformat the code to make more efficient

    public static void levelup(int currentLevel, int currentXP, int nextXP, ItemStack item) {
        NBTItem nbtItem = new NBTItem(item);
        if(currentXP >= nextXP) {
            currentLevel += 1;

            Bukkit.getConsoleSender().sendMessage("Level: " + currentLevel);

            nbtItem.setInteger("level", currentLevel);
            nbtItem.applyNBT(item);
        }
    }


    private boolean xpCheck(int currentXp, int xpNeeded) {
        return currentXp >= xpNeeded;
    }

    public static int calculateLevelByXp(int xp, String equation, int level, int maxLevel) {
        List<Integer> levels = new ArrayList<>();
        for(int i = 0; i < maxLevel; i++) {
            levels.add(calculateXPNextLevel(equation, level));
        }

        for(int i : levels) {
            if(xp / i > 0) {
                level++;
            }
            else break;

            if(i == maxLevel) {
                break;
            }
        }

        return level - 1;
    }

    public static int calculateXPNextLevel(String equation, int level) {
        return Integer.parseInt(VariableSystem.solveEquation(VariableSystem.convertToInt(equation, "level", level)));
    }

    public static String getProgressBar(int current, int max, int totalBars, char symbol, ChatColor completedColor, ChatColor notCompletedColor) {
        float percent = (float) current / max;
        int progressBars = (int) (totalBars * percent);

        int count = totalBars - progressBars;

        if(count <= 0) {
            count = 1;
        }

        return Strings.repeat("" + completedColor + symbol, progressBars) + Strings.repeat("" + notCompletedColor + symbol, count);
    }
}
