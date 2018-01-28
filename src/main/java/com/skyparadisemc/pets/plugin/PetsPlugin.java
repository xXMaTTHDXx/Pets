package com.skyparadisemc.pets.plugin;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.skyparadisemc.pets.inject.PetModule;
import org.bukkit.plugin.java.JavaPlugin;

@Singleton
public class PetsPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        long start = System.currentTimeMillis();
        Guice.createInjector(new PetModule()).injectMembers(this);
        System.out.println(System.currentTimeMillis() - start);
    }

    @Override
    public void onDisable() {

    }

    @Inject
    private void enable() {

    }
}
