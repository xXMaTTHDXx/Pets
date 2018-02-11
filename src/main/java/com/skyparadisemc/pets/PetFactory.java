package com.skyparadisemc.pets;

import com.skyparadisemc.pets.abilities.Ability;
import org.bukkit.entity.EntityType;

public interface PetFactory {

    Pet fromJson(EntityType type, String name, Ability ability);

    Pet create(EntityType type, String name, Ability ability);
}
