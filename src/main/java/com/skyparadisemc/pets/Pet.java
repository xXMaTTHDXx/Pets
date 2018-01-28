package com.skyparadisemc.pets;

import com.google.inject.assistedinject.Assisted;
import com.skyparadisemc.pets.abilities.Ability;
import lombok.Data;
import org.bukkit.entity.EntityType;

import javax.inject.Inject;

@Data
public class Pet {

    private String name;
    private String customName = "";

    private Ability ability;

    private EntityType type;

    private boolean active = false;

    public @Inject Pet(@Assisted EntityType type, @Assisted String name, @Assisted Ability ability) {
        this.type = type;
        this.name = name;
        this.ability = ability;
        this.active = true;
    }
}
