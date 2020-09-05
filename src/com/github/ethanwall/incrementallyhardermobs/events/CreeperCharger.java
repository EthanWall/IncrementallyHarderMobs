package com.github.ethanwall.incrementallyhardermobs.events;

import com.github.ethanwall.incrementallyhardermobs.IncrementallyHarderMobs;
import com.github.ethanwall.incrementallyhardermobs.MobDifficultyHandler;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class CreeperCharger implements Listener {

    private int chargeLevel = IncrementallyHarderMobs.config.getInt("creeper-charged-level");

    @EventHandler
    public void onEntitySpawned(EntitySpawnEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Creeper && MobDifficultyHandler.stage >= chargeLevel) {
            Creeper creeper = (Creeper) entity;
            creeper.setPowered(true);
        }
    }

}
