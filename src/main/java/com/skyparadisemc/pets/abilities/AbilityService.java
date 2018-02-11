package com.skyparadisemc.pets.abilities;

import javax.inject.Inject;
import java.util.Optional;
import java.util.Set;

public class AbilityService {

    public @Inject
    Set<Ability> abilities;

    public Optional<Ability> getAbility(String name) {
        for (Ability a : abilities) {
            if (a.getName().equalsIgnoreCase(name)) {
                return Optional.of(a);
            }
        }
        return Optional.empty();
    }
}
