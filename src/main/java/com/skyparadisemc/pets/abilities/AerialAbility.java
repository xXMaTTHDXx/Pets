package com.skyparadisemc.pets.abilities;

import com.skyparadisemc.pets.Pet;
import com.skyparadisemc.pets.PetService;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AerialAbility implements Ability {

    private @Inject PetService petService;

    @Override
    public void onInteract(PlayerInteractEvent event) {

        Player player = event.getPlayer();

        Pet pet = petService.getPet(player);

        for (Entity e : pet.getEntity().getNearbyEntities(5,5,5)) {

            if (!(e instanceof Player))
                continue;

            if (e == player)
                continue;

            Player p = (Player) e;

            p.getVelocity().add(new Vector(p.getVelocity().getX(), 10, p.getVelocity().getZ()).multiply(-1));
        }

    }

    @Override
    public String getName() {
        return "aerial";
    }

    @Override
    public long getCooldown() {
        return 0;
    }
}
