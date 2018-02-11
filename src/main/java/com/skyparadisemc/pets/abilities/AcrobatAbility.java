package com.skyparadisemc.pets.abilities;

import com.skyparadisemc.pets.Pet;
import com.skyparadisemc.pets.PetService;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.concurrent.TimeUnit;

@Singleton
public class AcrobatAbility implements Ability {

    private @Inject
    PetService petService;

    @Override
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        Pet pet = petService.getPet(player);

        Location petLocation = pet.getLocation();

        for (Entity e : pet.getEntity().getNearbyEntities(5, 5, 5)) {

            if (!(e instanceof Player))
                continue;

            if (e == player)
                continue;

            Player p = (Player) e;

            p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20 * 10, 1));
            p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 20 * 5, 1));
            p.sendMessage(player.getName() + "'s " + pet.getCustomName() + " has used the: " + getName() + " special!");
            break;
        }
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
