package com.github.ethanwall.incrementallyhardermobs;

import com.github.ethanwall.incrementallyhardermobs.events.EntitySpawnListener;
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

        getServer().getPluginManager().registerEvents(new EntitySpawnListener(), this);

        long taskInterval = config.getLong("interval");
        getServer().getScheduler().runTaskTimer(this, new Runnable() {
            @Override
            public void run() {
                MobDifficultyHandler.stage++;
                MobDifficultyHandler.refreshDifficultMobs();
            }
        }, taskInterval, taskInterval);

        getLogger().info(pdf.getFullName() + " has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info(pdf.getFullName() + " has been disabled!");
    }

}
