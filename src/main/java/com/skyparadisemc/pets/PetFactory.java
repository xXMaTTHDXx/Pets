package com.skyparadisemc.pets;

import com.google.inject.assistedinject.Assisted;
import com.skyparadisemc.pets.abilities.Ability;
import org.bukkit.entity.EntityType;

import javax.inject.Named;

public interface PetFactory {

    Pet fromJson(EntityType type, String name, Ability ability);

    Pet create(EntityType type, String name, Ability ability);
}
