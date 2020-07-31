package com.github.ethanwall.incrementallyhardermobs.events;

import com.github.ethanwall.incrementallyhardermobs.MobDifficultyHandler;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class EntitySpawnListener implements Listener {

    @EventHandler
    public void OnEntitySpawned(EntitySpawnEvent event) {
        if (event.getEntity() instanceof LivingEntity) {
            LivingEntity entity = (LivingEntity) event.getEntity();
            MobDifficultyHandler.createDifficultMob(entity);
        }
    }

}
