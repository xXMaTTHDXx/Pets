package com.skyparadisemc.pets.listeners;

import com.skyparadisemc.pets.Pet;
import com.skyparadisemc.pets.PetService;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class InventoryListener implements Listener {

    private @Inject JavaPlugin plugin;
    private @Inject PetService petService;

    @EventHandler
    public void onClick(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();

        if (event.getClickedInventory() == null || event.getInventory() == null) return;

        if (!event.getInventory().getTitle().equalsIgnoreCase("Battle Buddies")) return;

        event.setResult(Event.Result.DENY);

        Inventory inv = event.getClickedInventory();

        ItemStack item = event.getCurrentItem();

        if (item == null || item.getType() == Material.AIR) return;

        String typeName = item.getItemMeta().getDisplayName();

        EntityType type = EntityType.valueOf(typeName.toUpperCase());

        Pet pet = petService.getPet(player);

        if (pet != null) {

            Entity e = pet.getEntity();

            if (e == null) {
                return;
            }

            if (!e.isDead()) {
                e.remove();
            }
        }

        petService.getPlayerPets().remove(player.getUniqueId());
        petService.createPet(player, type);

        player.closeInventory();
        player.sendMessage(ChatColor.GREEN + "Created your pet!");
    }
}
