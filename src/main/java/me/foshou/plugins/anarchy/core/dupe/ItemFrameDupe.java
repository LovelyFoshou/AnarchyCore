package me.foshou.plugins.anarchy.core.dupe;

import me.foshou.plugins.anarchy.core.Main;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemFrame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import java.util.Random;

public class ItemFrameDupe implements Listener {
    @EventHandler
    public void onRotate(PlayerInteractEntityEvent event) {
        if (!Main.config.getBoolean("dupe.item_frame.enable")) return;
        if (!event.getPlayer().hasPermission("anarchy.dupe.frame")) return;
        if (event.getRightClicked().getType() == EntityType.ITEM_FRAME) {
            ItemFrame itemFrame = (ItemFrame) event.getRightClicked();

            if (new Random().nextInt(100)+1<= Main.config.getInt("dupe.item_frame.odds")) {
                itemFrame.getWorld().dropItem(itemFrame.getLocation(), itemFrame.getItem());
            }
        }
    }
}
