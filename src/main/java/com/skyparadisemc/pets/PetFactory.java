package com.skyparadisemc.pets;

import com.skyparadisemc.pets.abilities.Ability;
import org.bukkit.entity.EntityType;

public interface PetFactory {

    public Pet create(EntityType type, String name, Ability ability);
}
