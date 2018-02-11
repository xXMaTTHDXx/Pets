package com.skyparadisemc.pets.abilities;

import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public interface Ability {

    default void onInteract(PlayerInteractEvent event) {
    }

    default void onDamage(EntityDamageByEntityEvent event) {
    }

    public String getName();

    public long getCooldown();
}
