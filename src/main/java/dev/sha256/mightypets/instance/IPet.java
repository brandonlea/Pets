package dev.sha256.mightypets.instance;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import javax.script.ScriptException;

public interface IPet {

    void register() throws ScriptException;

    void givePlayer(Player player);

    void onClick(PlayerInteractEvent event);

    boolean checkID(String id);

    void giveXp(int xp, Player player);

    void setItem(ItemStack item);


}
