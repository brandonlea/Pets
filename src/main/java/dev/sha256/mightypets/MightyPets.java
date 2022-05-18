package dev.sha256.mightypets;

import dev.sha256.mightypets.Commands.PetCommand;
import dev.sha256.mightypets.Pets.PigPet;
import dev.sha256.mightypets.instance.IPet;
import dev.sha256.mightypets.listener.TestListener;
import dev.sha256.mightypets.manager.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class MightyPets extends JavaPlugin {

    private static MightyPets plugin;

    private ArrayList<IPet> pets;

    private ConfigManager config;
    private ConfigManager petConfig;

    //Pets
    private PigPet pigPet;


    @Override
    public void onEnable() {
        // Plugin startup logic

        plugin = this;

        //Register Config files
        config = new ConfigManager("config", true, this);
        config.setup();

        petConfig = new ConfigManager("pets", true, this);
        petConfig.setup();

        //Register Pets Here
        pigPet = new PigPet(this);

        pigPet.register();

        pets = new ArrayList<>();

        pets.add(pigPet);

        //Register Events
        Bukkit.getPluginManager().registerEvents(pigPet, this);



        getCommand("pets").setExecutor(new PetCommand(this));


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public ConfigManager getMainConfig() {
        return config;
    }

    public ArrayList<IPet> getPets() {
        return pets;
    }

    public ConfigManager getPetConfig() {
        return petConfig;
    }

    public static MightyPets getPlugin() {
        return plugin;
    }
}
