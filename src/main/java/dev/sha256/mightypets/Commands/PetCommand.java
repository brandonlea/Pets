package dev.sha256.mightypets.Commands;

import dev.sha256.mightypets.MightyPets;
import dev.sha256.mightypets.instance.Pet;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PetCommand implements CommandExecutor {

    private MightyPets pets;

    public PetCommand(MightyPets pets) {
        this.pets = pets;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;

            if(args[0].equalsIgnoreCase("give")) {
                if(args.length == 1) {
                    player.sendMessage("Please do /pet give <pet>");
                }

                if(args.length == 2) {
                    for(Pet pet : pets.getPets()) {
                    }
                }
            }

        }

        return false;
    }
}
