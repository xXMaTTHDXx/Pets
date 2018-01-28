package com.skyparadisemc.pets.commands;

import com.skyparadisemc.pets.Pet;
import com.skyparadisemc.pets.PetService;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PetCommand implements SpigotCommand {

    private @Inject PetService petService;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!cmd.getName().equalsIgnoreCase("pets")) {
            return true;
        }

        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            //TODO open pet selection
        }

        if (args.length == 2) {

            if (args[0].equalsIgnoreCase("name")) {

                String name = args[1];

                Pet pet = petService.getPet(player);

                if (pet == null) {
                    player.sendMessage(ChatColor.RED + "You do not have a pet enabled!");
                    return true;
                }


            }
        }


        return false;
    }

    @Override
    public String getName() {
        return "pets";
    }
}
