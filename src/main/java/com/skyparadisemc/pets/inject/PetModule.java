package com.skyparadisemc.pets.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.skyparadisemc.pets.PetFactory;
import com.skyparadisemc.pets.data.DataService;
import com.skyparadisemc.pets.data.JsonDataService;
import com.skyparadisemc.pets.plugin.PetsPlugin;
import org.bukkit.plugin.java.JavaPlugin;

public class PetModule extends AbstractModule {

    private JavaPlugin plugin;

    public PetModule(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    protected void configure() {
        bind(DataService.class).to(JsonDataService.class);
        bind(JavaPlugin.class).toInstance(plugin);
        install(new FactoryModuleBuilder().build(PetFactory.class));
    }
}
