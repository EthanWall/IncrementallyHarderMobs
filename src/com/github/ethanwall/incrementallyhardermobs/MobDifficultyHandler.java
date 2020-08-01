package com.github.ethanwall.incrementallyhardermobs;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
        if (section == null) return;

        ConfigurationSection equipmentSection = section.getConfigurationSection("equipment");
        if (equipmentSection != null) {
            ItemStack[] armor = new ItemStack[4];
            armor[3] = equipmentSection.getItemStack("head");
            armor[2] = equipmentSection.getItemStack("body");
            armor[1] = equipmentSection.getItemStack("legs");
            armor[0] = equipmentSection.getItemStack("feet");
            entity.getEquipment().setArmorContents(armor);
            entity.getEquipment().setItemInMainHand(equipmentSection.getItemStack("main"));
            entity.getEquipment().setItemInOffHand(equipmentSection.getItemStack("secondary"));

            if (IncrementallyHarderMobs.debug) {
                if (equipmentSection.getItemStack("main") != null)
                    Bukkit.getLogger().info("Main Hand: " + equipmentSection.getItemStack("main").getType());
                if (equipmentSection.getItemStack("secondary") != null)
                    Bukkit.getLogger().info("Secondary: " + equipmentSection.getItemStack("secondary").getType());
                if (equipmentSection.getItemStack("head") != null)
                    Bukkit.getLogger().info("Head: " + equipmentSection.getItemStack("head").getType());
                if (equipmentSection.getItemStack("body") != null)
                    Bukkit.getLogger().info("Body: " + equipmentSection.getItemStack("body").getType());
                if (equipmentSection.getItemStack("legs") != null)
                    Bukkit.getLogger().info("Legs: " + equipmentSection.getItemStack("legs").getType());
                if (equipmentSection.getItemStack("feet") != null)
                    Bukkit.getLogger().info("Feet: " + equipmentSection.getItemStack("feet").getType());
            }
        }

        ConfigurationSection effectsSection = section.getConfigurationSection("effects");
        if (effectsSection != null) {
            for (String key : effectsSection.getKeys(false)) {
                entity.addPotionEffect(new PotionEffect(PotionEffectType.getByName(key), Integer.MAX_VALUE, effectsSection.getInt(key)));

                if (IncrementallyHarderMobs.debug) {
                    Bukkit.getLogger().info(String.format("Effect %s (%dx) has been added.", key, effectsSection.getInt(key)));
                }
            }
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
