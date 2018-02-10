package com.skyparadisemc.pets.commands;

import com.skyparadisemc.pets.Pet;
import com.skyparadisemc.pets.PetService;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PetCommand implements SpigotCommand {

    private @Inject JavaPlugin plugin;

    private @Inject PetService petService;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!cmd.getName().equalsIgnoreCase("bb") || cmd.getName().equalsIgnoreCase("battlebuddies")) {
            return true;
        }

        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            //TODO open pet selection
            openInventory(player);
        }

        if (args.length >= 2) {

            if (args[0].equalsIgnoreCase("name")) {

                StringBuilder builder = new StringBuilder();

                for (int i = 1; i < args.length; i++) {
                    builder.append(args[i]);
                    if (i - 1 < args.length) {
                        builder.append(" ");
                    }
                }

                Pet pet = petService.getPet(player);

                if (pet == null) {
                    player.sendMessage(ChatColor.RED + "You do not have a pet enabled!");
                    return true;
                }
                pet.setCustomName(builder.toString());
                pet.update();
            }
        }


        return false;
    }

    public void openInventory(Player player) {
        Inventory inv = plugin.getServer().createInventory(null, 9, "Battle Buddies");

        for (EntityType type : petService.getPetTypes()) {

            ItemStack item = new ItemStack(Material.PAPER, 1);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(ChatColor.GREEN + type.name().toUpperCase());
            item.setItemMeta(meta);
            inv.addItem(item);
        }

        player.openInventory(inv);
    }

    @Override
    public String getName() {
        return "bb";
    }
}
