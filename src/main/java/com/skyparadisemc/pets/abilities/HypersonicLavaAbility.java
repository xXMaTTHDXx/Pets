package com.skyparadisemc.pets.abilities;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.skyparadisemc.pets.Pet;
import com.skyparadisemc.pets.PetService;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

@Singleton
public class HypersonicLavaAbility implements Ability {
    private @Inject
    PetService petService;

    @Override
    public void onDamage(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player))
            return;

        if (!(event.getEntity() instanceof Player))
            return;

        Player player = (Player) event.getDamager();

        //TODO add that custom enchantment stuff, I have no idea how.
        Player damaged = (Player) event.getEntity();

        Pet pet = petService.getPet(player);
        if (pet == null)
            return;

        damaged.setVelocity(new Vector(0, 4, 0));
        damaged.setFireTicks(2 * 20);
    }

    @Override
    public String getName() {
        return "Hypersonic Lava";
    }

    @Override
    public long getCooldown() {
        return 0;
    }
}
