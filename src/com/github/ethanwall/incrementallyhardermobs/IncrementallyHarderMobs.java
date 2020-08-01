package com.github.ethanwall.incrementallyhardermobs;

import com.github.ethanwall.incrementallyhardermobs.commands.ChangeStageCommand;
import com.github.ethanwall.incrementallyhardermobs.events.EntitySpawnListener;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class IncrementallyHarderMobs extends JavaPlugin {

    public static FileConfiguration config;
    public static boolean debug;
    public static StageScoreboard scoreboard;

    private PluginDescriptionFile pdf;

    @Override
    public void onEnable() {
        pdf = getDescription();

        saveDefaultConfig();
        config = getConfig();

        debug = config.getBoolean("debug");

        scoreboard = new StageScoreboard();

        getServer().getPluginManager().registerEvents(new EntitySpawnListener(), this);
        getServer().getPluginManager().registerEvents(scoreboard, this);

        getCommand("setstage").setExecutor(new ChangeStageCommand());

        long taskInterval = config.getLong("interval");
        getServer().getScheduler().runTaskTimer(this, () -> {
            MobDifficultyHandler.stage++;
            MobDifficultyHandler.refreshDifficultMobs();
            scoreboard.updateScoreboard();
        }, taskInterval, taskInterval);

        getLogger().info(pdf.getFullName() + " has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info(pdf.getFullName() + " has been disabled!");
    }

}
