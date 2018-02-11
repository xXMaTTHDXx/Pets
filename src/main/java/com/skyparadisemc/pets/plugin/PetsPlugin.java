package com.skyparadisemc.pets.plugin;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.skyparadisemc.pets.PetService;
import com.skyparadisemc.pets.commands.SpigotCommand;
import com.skyparadisemc.pets.inject.PetModule;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Set;

@Singleton
public class PetsPlugin extends JavaPlugin {

    private @Inject
    Set<Listener> listenerSet;
    private @Inject
    Set<SpigotCommand> commandsSet;
    private @Inject
    PetService petService;

    @Override
    public void onEnable() {
        long start = System.currentTimeMillis();
        Injector injector = Guice.createInjector(new PetModule(this));

        System.out.println(System.currentTimeMillis() - start);

        enable();
    }

    @Override
    public void onDisable() {

    }


    private void enable() {
        for (Listener l : listenerSet) {
            getServer().getPluginManager().registerEvents(l, this);
        }

        for (SpigotCommand c : commandsSet) {

            getCommand(c.getName()).setExecutor(c);
        }

        petService.init();
    }
}
