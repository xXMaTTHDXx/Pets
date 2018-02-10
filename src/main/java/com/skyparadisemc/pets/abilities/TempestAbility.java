package com.skyparadisemc.pets.abilities;

import com.skyparadisemc.pets.Pet;
import com.skyparadisemc.pets.PetService;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.concurrent.TimeUnit;

@Singleton
public class TempestAbility implements Ability {

    private @Inject PetService petService;

    @Override
    public void onInteract(PlayerInteractEvent event) {
        Player pl = event.getPlayer();

        Pet pet = petService.getPet(pl);

        for (Entity e : pet.getEntity().getNearbyEntities(5, 5,5)) {

            if (!(e instanceof Player))
                continue;

            if (e == pl)
                continue;


            ((Player)e).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20*5, 1));
            e.sendMessage(pl.getName() + "'s " + pet.getCustomName() + " has used the: " + getName() + " special!");
            break;
        }
    }

    @Override
    public String getName() {
        return "tempest";
    }

    @Override
    public long getCooldown() {
        return TimeUnit.MINUTES.toMillis(5);
    }
}
