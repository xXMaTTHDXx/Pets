package com.skyparadisemc.pets.abilities;

import com.skyparadisemc.pets.Pet;
import com.skyparadisemc.pets.PetService;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.concurrent.TimeUnit;

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

            p.setVelocity(p.getLocation().getDirection().multiply(-1).add(new Vector(0, 10, 0)));
            p.sendMessage(ChatColor.RED + "You have been hit with " + player.getName() + "'s " + getName() + " ability!");
        }

    }

    @Override
    public String getName() {
        return "aerial";
    }

    @Override
    public long getCooldown() {
        return TimeUnit.MINUTES.toMillis(5);
    }
}
