package com.github.ethanwall.incrementallyhardermobs;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.*;

public class StageScoreboard implements Listener {

    ScoreboardManager manager = Bukkit.getScoreboardManager();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        createScoreboard(event.getPlayer());
        updateScoreboard();
    }

    public void createScoreboard(Player player) {
        String title = IncrementallyHarderMobs.config.getString("scoreboard-title");
        final Scoreboard scoreboard = manager.getNewScoreboard();
        final Objective objective = scoreboard.registerNewObjective("mobstage", "dummy", title);
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        player.setScoreboard(scoreboard);
    }

    public void updateScoreboard() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            Score score = player.getScoreboard().getObjective("mobstage").getScore(ChatColor.GREEN + "Stage:");
            score.setScore(MobDifficultyHandler.stage);
        }
    }

}
