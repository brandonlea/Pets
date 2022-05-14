package dev.sha256.pets;

import dev.sha256.pets.manager.ConfigManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Pets extends JavaPlugin {

    private ConfigManager config;
    private ConfigManager petsConfig;

    @Override
    public void onEnable() {
        // Plugin startup logic
        config = new ConfigManager("config", false, this);
        petsConfig = new ConfigManager("pets", false, this);
        config.setup();
        petsConfig.setup();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public ConfigManager getMainConfig() {
        return config;
    }

    public ConfigManager getPetsConfig() {
        return petsConfig;
    }
}
