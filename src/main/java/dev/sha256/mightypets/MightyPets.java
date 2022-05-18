package dev.sha256.mightypets;

import dev.sha256.mightypets.Commands.PetCommand;
import dev.sha256.mightypets.Pets.InterestPet;
import dev.sha256.mightypets.instance.Pet;
import dev.sha256.mightypets.manager.ConfigManager;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class MightyPets extends JavaPlugin {


    //Hooks
    private static Economy economy = null;

    private static MightyPets plugin;

    private List<Pet> pets;

    private ConfigManager config;
    private ConfigManager petConfig;




    @Override
    public void onEnable() {
        // Plugin startup logic

        plugin = this;

        pets = new ArrayList<>();

        //Register Config files
        config = new ConfigManager("config", true, this);
        config.setup();

        petConfig = new ConfigManager("pets", true, this);
        petConfig.setup();


        //Register Pets
        pets.add(new InterestPet(this));
        //Register Events



        getCommand("pets").setExecutor(new PetCommand(this));


        //Hooks
        if (!setupEconomy() ) {
            Bukkit.getConsoleSender().sendMessage(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        economy = rsp.getProvider();
        return economy != null;
    }

    public ConfigManager getMainConfig() {
        return config;
    }

    public ConfigManager getPetConfig() {
        return petConfig;
    }

    public static MightyPets getPlugin() {
        return plugin;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public static Economy getEconomy() {
        return economy;
    }
}
