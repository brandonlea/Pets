package dev.sha256.pets.manager;

import dev.sha256.pets.Pets;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManager {

    private String name, folder;
    private File file;
    private boolean isDefault;
    private Pets pets;

    private YamlConfiguration configuration;

    public ConfigManager(String name, String folder, boolean isDefault, Pets pets) {
        this.name = name;
        this.folder = folder;
        this.isDefault = isDefault;
        this.pets = pets;

        this.file = new File(pets.getDataFolder(), folder + File.separator + name + ".yml");
    }

    public ConfigManager(String name, boolean isDefault, Pets pets) {
        this.name = name;
        this.isDefault = isDefault;
        this.pets = pets;

        this.file = new File(pets.getDataFolder(), name + ".yml");
    }

    public void setup() {
        if(!this.file.exists()) {
            this.file.getParentFile().mkdirs();

            try {
                this.file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        configuration = YamlConfiguration.loadConfiguration(this.file);
    }
}
