package com.skyparadisemc.pets;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.skyparadisemc.pets.abilities.AbilityFactory;
import com.skyparadisemc.pets.data.DataService;
import lombok.Data;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

@Data
@Singleton
public class PetService {

    private @Inject DataService dataService;

    private @Inject AbilityFactory abilityFactory;

    private @Inject PetFactory petFactory;

    public Pet getPet(Player player) {

        Pet pet = petFactory.create(EntityType.CHICKEN, "Chicken", abilityFactory.create("yeet"));

        return pet;
    }
}
