package com.github.ethanwall.incrementallyhardermobs;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class MobDifficultyHandler {

    public static int stage = 0;

    public static void createDifficultMob(LivingEntity entity) {
        EntityType type = entity.getType();
        ConfigurationSection section = IncrementallyHarderMobs.config.getConfigurationSection(String.format("mobs.%s.%d", type.name(), stage));

        ConfigurationSection equipmentSection = section.getConfigurationSection("equipment");
        ItemStack[] armor = new ItemStack[4];
        armor[0] = equipmentSection.getItemStack("head");
        armor[1] = equipmentSection.getItemStack("body");
        armor[2] = equipmentSection.getItemStack("legs");
        armor[3] = equipmentSection.getItemStack("feet");
        entity.getEquipment().setArmorContents(armor);
        entity.getEquipment().setItemInMainHand(equipmentSection.getItemStack("main"));
        entity.getEquipment().setItemInOffHand(equipmentSection.getItemStack("secondary"));

        ConfigurationSection effectsSection = section.getConfigurationSection("effects");
        for (String key : effectsSection.getKeys(false)) {
            entity.addPotionEffect(new PotionEffect(PotionEffectType.getByName(key), Integer.MAX_VALUE, effectsSection.getInt(key)));
        }
    }

    public static void refreshDifficultMobs() {
        for (World world : Bukkit.getWorlds()) {
            for (LivingEntity entity : world.getLivingEntities()) {
                createDifficultMob(entity);
            }
        }
    }

}
