package dev.sha256.mightypets.manager;

import dev.sha256.mightypets.MightyPets;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManager {

    private String name, folder;
    private File file;
    private boolean isDefault;
    private MightyPets mightyPets;

    private YamlConfiguration configuration;

    public ConfigManager(String name, String folder, boolean isDefault, MightyPets mightyPets) {
        this.name = name;
        this.folder = folder;
        this.isDefault = isDefault;
        this.mightyPets = mightyPets;

        this.file = new File(mightyPets.getDataFolder(), folder + File.separator + name + ".yml");
    }

    public ConfigManager(String name, boolean isDefault, MightyPets mightyPets) {
        this.name = name;
        this.isDefault = isDefault;
        this.mightyPets = mightyPets;

        this.file = new File(mightyPets.getDataFolder(), name + ".yml");
    }

    public void setup() {
        if(!this.file.exists()) {
            this.file.getParentFile().mkdirs();

           if(!isDefault) {
               try {
                   this.file.createNewFile();
                   save();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           } else {
               mightyPets.saveResource(name + ".yml", false);
           }
        }


        configuration = YamlConfiguration.loadConfiguration(this.file);
    }

    public void save() {
        try {
            this.configuration.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public YamlConfiguration getConfiguration() {
        return configuration;
    }

    public File getFile() {
        return file;
    }
}
