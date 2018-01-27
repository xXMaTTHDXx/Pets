package com.skyparadisemc.pets.plugin;

import com.google.inject.Guice;
import com.skyparadisemc.pets.PetModule;
import org.bukkit.plugin.java.JavaPlugin;

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
}
