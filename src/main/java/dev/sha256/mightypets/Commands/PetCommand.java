package dev.sha256.mightypets.Commands;

import de.tr7zw.nbtapi.NBTItem;
import dev.sha256.mightypets.MightyPets;
import dev.sha256.mightypets.instance.IPet;
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
                    for(IPet pet : pets.getPets()) {
                        if(pet.checkID(args[1])) {
                            pet.givePlayer(player);
                        }
                    }
                }
            }

            if(args[0].equalsIgnoreCase("xp")) {
                if(args.length == 1) {
                    player.sendMessage("Please do /pet xp <amount>");
                }

                if(args.length == 2) {
                    NBTItem item = new NBTItem(player.getInventory().getItemInMainHand());

                    for(IPet pet : pets.getPets()) {
                        if(pet.checkID(item.getString("id"))) {
                            pet.setItem(item.getItem());
                            pet.giveXp(Integer.parseInt(args[1]), player);
                        }
                    }
                }
            }
        }

        return false;
    }
}
