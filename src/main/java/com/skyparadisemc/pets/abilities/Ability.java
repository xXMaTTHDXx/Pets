package com.skyparadisemc.pets.abilities;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import lombok.Data;
import org.bukkit.entity.EntityType;

@Data
public class Ability {

    private String name;

    public @Inject Ability(@Assisted String name) {
        this.name = name;
    }
}
