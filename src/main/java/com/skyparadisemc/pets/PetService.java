package com.skyparadisemc.pets;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.skyparadisemc.pets.abilities.Ability;
import com.skyparadisemc.pets.abilities.AbilityService;
import com.skyparadisemc.pets.data.DataService;
import lombok.Data;
import lombok.Getter;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

@Data
@Singleton
public class PetService {

    private @Inject DataService dataService;

    private @Inject AbilityService abilityFactory;

    private @Inject PetFactory petFactory;

    private @Inject JavaPlugin plugin;

    private @Getter Map<UUID, Map<Ability, Long>> cooldowns = Maps.newHashMap();

    private HashMap<UUID, Pet> playerPets = Maps.newHashMap();
    private List<EntityType> petTypes = Lists.newArrayList();

    public void init() {
        petTypes.add(EntityType.PIG);

        plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, this::updatePets, 0L, 20L);
    }

    public Map<Ability, Long> getPlayerCooldowns(Player player) {
        Map<Ability, Long> cooldown = cooldowns.get(player.getUniqueId());

        if (cooldown == null) {
            return new HashMap<>();
        }

        return cooldown;
    }

    public boolean isCoolingDown(Player player, Ability ability) {
        Map<Ability, Long> pDowns = cooldowns.get(player.getUniqueId());

        Long cooldown = pDowns.get(ability);

        if (cooldown == null) {
            return false;
        }

        return System.currentTimeMillis() >= cooldown;
    }

    public void updatePets() {

        for (Map.Entry<UUID, Pet> entry : playerPets.entrySet()) {

            Pet pet = entry.getValue();

            Player player = plugin.getServer().getPlayer(entry.getKey());

            pet.follow(player);
        }
    }

    public void renamePet(Player player, String customName) {
        Pet pet = getPet(player);

        if (pet == null) {
            return;
        }

        pet.setCustomName(customName);
    }

    public Pet createPet(Player player, EntityType type) {
        Pet pet = petFactory.create(type, player.getName() + "'s Pet", abilityFactory.getAbility("empty").get());

        this.playerPets.put(player.getUniqueId(), pet);

        pet.spawn(player.getLocation());
        pet.follow(player);

        return pet;
    }

    public Pet getPet(Player player) {

        return playerPets.get(player.getUniqueId());
    }
}
