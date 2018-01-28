package com.skyparadisemc.pets;

import com.google.inject.assistedinject.Assisted;
import com.skyparadisemc.pets.abilities.Ability;
import lombok.Data;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.java.JavaPlugin;

import javax.inject.Inject;

@Data
public class Pet {

    private String name;
    private String customName = "";

    private Ability ability;

    private EntityType type;

    private boolean active = false;

    private Entity entity = null;

    private Location location = null;

    public @Inject Pet(@Assisted EntityType type, @Assisted String name, @Assisted Ability ability) {
        this.type = type;
        this.name = name;
        this.ability = ability;
        this.active = true;
    }

    public Location getLocation() {
        return entity == null ? null : entity.getLocation();
    }

    public Entity spawn(Location location) {
        if (this.active) {
            return null;
        }

        Entity ent = location.getWorld().spawn(location, type.getEntityClass());

        if (customName.equalsIgnoreCase("")) {

        }
        else {
            ent.setCustomName(customName);
            ent.setCustomNameVisible(true);
        }
        this.entity = ent;
        return ent;
    }
}
