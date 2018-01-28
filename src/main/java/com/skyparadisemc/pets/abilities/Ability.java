package com.skyparadisemc.pets.abilities;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import lombok.Data;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public interface Ability {

    default void onInteract(PlayerInteractEvent event) {}
    default void onDamage(EntityDamageByEntityEvent event) {}

    public String getName();
}
