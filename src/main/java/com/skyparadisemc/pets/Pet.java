package com.skyparadisemc.pets;

import com.skyparadisemc.pets.abilities.Ability;
import lombok.Data;
import org.bukkit.entity.EntityType;

@Data
public class Pet {

    private String name;
    private String customName;

    private Ability ability;

    private EntityType type;
}
