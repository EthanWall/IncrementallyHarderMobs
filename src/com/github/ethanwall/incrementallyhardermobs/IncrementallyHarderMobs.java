package com.github.ethanwall.incrementallyhardermobs;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class IncrementallyHarderMobs extends JavaPlugin {

    public static FileConfiguration config;

    private PluginDescriptionFile pdf;

    @Override
    public void onEnable() {
        pdf = getDescription();

        saveDefaultConfig();

        config = getConfig();

        getLogger().info(pdf.getFullName() + " has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info(pdf.getFullName() + " has been disabled!");
    }

}
