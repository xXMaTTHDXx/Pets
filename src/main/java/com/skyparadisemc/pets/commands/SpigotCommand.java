package com.skyparadisemc.pets.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.defaults.BukkitCommand;

public interface SpigotCommand extends CommandExecutor {

    public String getName();
}
