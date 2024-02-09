package me.foshou.plugins.anarchy.core.features.limits;

import me.foshou.plugins.anarchy.core.Main;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class ChunkEntities implements Listener {
    @EventHandler
    public void onSpawn(EntitySpawnEvent event) {
        if (!Main.config.getBoolean("limits.CrystalSpeed.enable")) return;
        if (event.getEntityType().equals(EntityType.ENDER_CRYSTAL) || event.getEntityType().equals(EntityType.PRIMED_TNT)) return;
        if (event.getLocation().getChunk().getEntities().length> Main.config.getInt("limits.ChunkEntityAmount.max_entity")) event.setCancelled(true);
    }
}
