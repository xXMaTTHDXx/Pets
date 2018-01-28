package com.skyparadisemc.pets;

import com.google.common.collect.Maps;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.skyparadisemc.pets.abilities.AbilityService;
import com.skyparadisemc.pets.data.DataService;
import lombok.Data;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

@Data
@Singleton
public class PetService {

    private @Inject DataService dataService;

    private @Inject AbilityService abilityFactory;

    private @Inject PetFactory petFactory;

    private HashMap<UUID, Pet> playerPets = Maps.newHashMap();

    public void load() {

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
