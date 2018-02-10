package com.skyparadisemc.pets.inject;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.multibindings.Multibinder;
import com.skyparadisemc.pets.PetFactory;
import com.skyparadisemc.pets.PetService;
import com.skyparadisemc.pets.abilities.Ability;
import com.skyparadisemc.pets.abilities.AcrobatAbility;
import com.skyparadisemc.pets.abilities.AerialAbility;
import com.skyparadisemc.pets.commands.PetCommand;
import com.skyparadisemc.pets.commands.SpigotCommand;
import com.skyparadisemc.pets.data.DataService;
import com.skyparadisemc.pets.data.JsonDataService;
import com.skyparadisemc.pets.listeners.InventoryListener;
import com.skyparadisemc.pets.listeners.PlayerListener;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public class PetModule extends AbstractModule {

    private JavaPlugin plugin;

    public PetModule(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    protected void configure() {
        bind(DataService.class).to(JsonDataService.class);
        bind(JavaPlugin.class).toInstance(plugin);
        bind(Random.class).toInstance(new Random());
        bind(PetService.class).toInstance(new PetService());


        install(new FactoryModuleBuilder().build(PetFactory.class));

        Multibinder<Ability> abilityBinder = Multibinder.newSetBinder(binder(), Ability.class);
        abilityBinder.addBinding().to(AcrobatAbility.class);
        abilityBinder.addBinding().to(AerialAbility.class);

        Multibinder<Listener> listenerBinder = Multibinder.newSetBinder(binder(), Listener.class);
        listenerBinder.addBinding().to(InventoryListener.class);
        listenerBinder.addBinding().to(PlayerListener.class);

        Multibinder<SpigotCommand> commandMultibinder = Multibinder.newSetBinder(binder(), SpigotCommand.class);
        commandMultibinder.addBinding().to(PetCommand.class);

        requestInjection(plugin);
    }
}
