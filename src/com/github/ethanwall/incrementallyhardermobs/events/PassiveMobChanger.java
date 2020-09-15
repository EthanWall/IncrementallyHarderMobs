package com.github.ethanwall.incrementallyhardermobs.events;

import com.github.ethanwall.incrementallyhardermobs.IncrementallyHarderMobs;
import com.github.ethanwall.incrementallyhardermobs.MobDifficultyHandler;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PassiveMobChanger implements Listener {

    @EventHandler
    public void onEntitySpawned(EntitySpawnEvent event) {
        if (!(event.getEntity() instanceof LivingEntity)) return;

        EntityType type = event.getEntityType();
        ConfigurationSection section = IncrementallyHarderMobs.config.getConfigurationSection(String.format("mobs.%s.%d", type.name(), MobDifficultyHandler.stage));

        if (section == null || !section.contains("hostile") || !section.getBoolean("hostile")) return;

        LivingEntity riding = (LivingEntity) event.getLocation().getWorld().spawnEntity(event.getLocation(), EntityType.ZOMBIE);
        riding.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0));

        LivingEntity entity = (LivingEntity) event.getEntity();
        entity.addPassenger(riding);
    }

}
