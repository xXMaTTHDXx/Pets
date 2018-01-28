package com.skyparadisemc.pets;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.skyparadisemc.pets.abilities.AbilityService;
import com.skyparadisemc.pets.data.DataService;
import lombok.Data;
import org.bukkit.entity.Player;

@Data
public class PetService {

    @Inject
    private DataService dataService;

    @Inject
    private AbilityService abilityService;

    @Inject
    private Provider<Pet> petProvider;

    public Pet getPet(Player player) {

        Pet pet = petProvider.get();

        pet.setAbility(abilityService.forPet(pet));

        return pet;
    }
}
