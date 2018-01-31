package com.skyparadisemc.pets.listeners;

import com.skyparadisemc.pets.Pet;
import com.skyparadisemc.pets.PetService;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PlayerListener implements Listener {

    private @Inject PetService petService;

    @EventHandler
    public void onTeleport(PlayerTeleportEvent event) {
        Player player = event.getPlayer();

        Pet pet = petService.getPet(player);

        if (pet == null) {
            return;
        }

        pet.getEntity().teleport(event.getTo());
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();

        Pet pet = petService.getPet(player);

        if (pet == null) {
            return;
        }


    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();

        Pet pet = petService.getPet(player);

        if (pet == null) {
            return;
        }

        pet.getEntity().teleport(player.getLocation());
    }
}
