package com.skyparadisemc.pets;

import com.google.inject.assistedinject.Assisted;
import com.skyparadisemc.pets.abilities.Ability;
import lombok.Data;
import net.minecraft.server.v1_8_R3.EntityInsentient;
import net.minecraft.server.v1_8_R3.PathEntity;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

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

    public @Inject
    Pet(@Assisted EntityType type, @Assisted String name, @Assisted Ability ability) {
        this.type = type;
        this.name = name;
        this.ability = ability;
    }

    public void update() {
        entity.setCustomName(ChatColor.translateAlternateColorCodes('&', customName));
        entity.setCustomNameVisible(true);
    }

    public void follow(Player player) {

        if (entity == null) {
            return;
        }

        if (entity.isDead()) {
            return;
        }


        net.minecraft.server.v1_8_R3.Entity pett = ((CraftEntity) entity)
                .getHandle();
        ((EntityInsentient) pett).getNavigation().a(2);
        Object petf = ((CraftEntity) entity).getHandle();
        Location targetLocation = player.getLocation();
        PathEntity path;
        path = ((EntityInsentient) petf).getNavigation().a(
                targetLocation.getBlockX(), targetLocation.getBlockY(),
                targetLocation.getBlockZ());
        if (path != null) {
            ((EntityInsentient) petf).getNavigation().a(path, 1.0D);
            ((EntityInsentient) petf).getNavigation().a(2.0D);
        }
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

        } else {
            ent.setCustomName(customName);
            ent.setCustomNameVisible(true);
        }

        this.entity = ent;
        this.active = true;
        return ent;
    }
}
