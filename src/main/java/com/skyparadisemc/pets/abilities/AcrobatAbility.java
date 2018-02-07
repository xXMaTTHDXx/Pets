package com.skyparadisemc.pets.abilities;

import com.skyparadisemc.pets.Pet;
import com.skyparadisemc.pets.PetService;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import javax.inject.Inject;
import java.util.concurrent.TimeUnit;

public class AcrobatAbility implements Ability {

    private @Inject PetService petService;

    @Override
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        Pet pet = petService.getPet(player);

        
    }


    @Override
    public String getName() {
        return "acrobat";
    }

    @Override
    public long getCooldown() {
        return TimeUnit.MINUTES.toMillis(5);
    }
}
