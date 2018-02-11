package com.skyparadisemc.pets.abilities;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.skyparadisemc.pets.Pet;
import com.skyparadisemc.pets.PetService;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@Singleton
public class FusionFighterAbility implements Ability {
    private static final PotionEffect STUNNED_POTION_EFFECT_SLOW = new PotionEffect(PotionEffectType.SLOW, 2 * 20, 2);
    private static final PotionEffect STUNNED_POTION_EFFECT_CONFUSION = new PotionEffect(PotionEffectType.CONFUSION, 2 * 20, 1);
    private static final PotionEffect STUNNED_POTION_EFFECT_HARM = new PotionEffect(PotionEffectType.HARM, 20, 0);


    private @Inject
    PetService petService;

    @Override
    public void onDamage(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player))
            return;

        if (!(event.getEntity() instanceof Player))
            return;

        Player player = (Player) event.getDamager();
        Player damaged = (Player) event.getEntity();

        Pet pet = petService.getPet(player);
        if (pet == null)
            return;

        STUNNED_POTION_EFFECT_SLOW.apply(damaged);
        STUNNED_POTION_EFFECT_CONFUSION.apply(damaged);
        STUNNED_POTION_EFFECT_HARM.apply(damaged);
        damaged.sendMessage(ChatColor.RED + String.format("%s has used their pet's fusion fighter ability against you.", player.getName()));
    }

    @Override
    public String getName() {
        return "Stun";
    }

    @Override
    public long getCooldown() {
        return 0;
    }
}
