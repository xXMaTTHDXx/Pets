package com.skyparadisemc.pets.listeners;

import com.skyparadisemc.pets.Pet;
import com.skyparadisemc.pets.PetService;
import com.skyparadisemc.pets.abilities.Ability;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Map;
import java.util.UUID;

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
    public void onQuit(PlayerQuitEvent event) {
        if (petService.getPet(event.getPlayer()) != null) {
            petService.getPet(event.getPlayer()).getEntity().remove();
            petService.getPlayerPets().remove(event.getPlayer().getUniqueId());
        }
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
    public void onInteract(PlayerInteractEvent event) {

        Player player = event.getPlayer();

        Pet pet = petService.getPet(player);

        if (pet == null)
            return;

        Ability ability = pet.getAbility();

        if ((event.getAction() != Action.RIGHT_CLICK_AIR || event.getAction() != Action.RIGHT_CLICK_BLOCK) && !player.isSneaking()) {
            System.out.println("incorrect usage");
            return;
        }
        if (petService.isCoolingDown(player, ability))
            return;

        long current = System.currentTimeMillis();

        Map<Ability, Long> cooldowns = petService.getPlayerCooldowns(player);
        cooldowns.put(ability, (current + ability.getCooldown()));

        player.sendMessage(ChatColor.GREEN + "You used your pet's special!");

        ability.onInteract(event);

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
