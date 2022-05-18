package dev.sha256.mightypets.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class TestListener implements Listener {

    @EventHandler
    public void onShoot(EntityDamageByEntityEvent event) {
        if(event.getDamager().getType() == EntityType.ARROW) {
            event.setDamage(event.getDamage() * 3);
            Bukkit.getConsoleSender().sendMessage("Hit: " + event.getDamage());
        }
    }

}
