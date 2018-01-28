package com.skyparadisemc.pets.abilities;

import com.skyparadisemc.pets.Pet;
import com.skyparadisemc.pets.PetService;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Random;

@Singleton
public class CritAbility implements Ability {

    private @Inject PetService petService;

    private @Inject Random random;

    @Override
    public void onDamage(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getDamager();

        Pet pet = petService.getPet(player);

        if (!pet.isActive()) {
            return;
        }

        int ran = random.nextInt(100);

        if (ran <= 10) {
            event.setDamage(event.getDamage() + 10);
        }

        player.sendMessage(pet.getCustomName() + "Uses Crit!");
    }

    @Override
    public String getName() {
        return "Crit";
    }
}
