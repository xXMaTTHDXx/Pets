package com.skyparadisemc.pets;

import com.google.common.collect.Maps;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.skyparadisemc.pets.abilities.AbilityService;
import com.skyparadisemc.pets.data.DataService;
import lombok.Data;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Data
@Singleton
public class PetService {

    private @Inject DataService dataService;

    private @Inject AbilityService abilityFactory;

    private @Inject PetFactory petFactory;

    private @Inject JavaPlugin plugin;

    private HashMap<UUID, Pet> playerPets = Maps.newHashMap();

    public void init() {
        plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, this::updatePets, 0L, 20L);
    }

    public void updatePets() {

        for (Map.Entry<UUID, Pet> entry : playerPets.entrySet()) {

            Pet pet = entry.getValue();

            Player player = plugin.getServer().getPlayer(entry.getKey());

            if (pet.getLocation() == null) continue;

            if (pet.getLocation().distanceSquared(player.getLocation()) <= 10) continue;

            pet.getEntity().teleport(player.getLocation());
        }
    }

    public void renamePet(Player player, String customName) {
        Pet pet = getPet(player);

        if (pet == null) {
            return;
        }

        pet.setCustomName(customName);
    }

    public Pet createPet(Player player) {
        Pet pet = petFactory.create(player.getName());

        this.playerPets.put(player.getUniqueId(), pet);

        return pet;
    }

    public Pet getPet(Player player) {

        return playerPets.get(player.getUniqueId());
    }
}
