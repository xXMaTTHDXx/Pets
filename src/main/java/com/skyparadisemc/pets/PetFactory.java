package com.skyparadisemc.pets;

import com.skyparadisemc.pets.abilities.Ability;
import org.bukkit.entity.EntityType;

import javax.inject.Named;

public interface PetFactory {

    public Pet create(String name);

    public Pet fromJson(@Named("type") EntityType type, @Named("name") String name, @Named("ability") Ability ability);

    public Pet create(EntityType type, String name, Ability ability);
}
