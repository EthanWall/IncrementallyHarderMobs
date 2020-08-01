package com.github.ethanwall.incrementallyhardermobs.commands;

import com.github.ethanwall.incrementallyhardermobs.IncrementallyHarderMobs;
import com.github.ethanwall.incrementallyhardermobs.MobDifficultyHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ChangeStageCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) return false;
        try {
            Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
           sender.sendMessage(ChatColor.DARK_RED + "That is not a valid integer!");
           return true;
        }

        MobDifficultyHandler.stage = Integer.parseInt(args[0]);
        MobDifficultyHandler.refreshDifficultMobs();
        IncrementallyHarderMobs.scoreboard.updateScoreboard();

        sender.sendMessage(String.format("The difficulty stage has been updated to %s%d%s.",
                ChatColor.BLUE,
                MobDifficultyHandler.stage,
                ChatColor.RESET));

        return true;
    }

}
