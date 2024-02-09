package me.foshou.plugins.anarchy.core.dupe;

import me.foshou.plugins.anarchy.core.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class CommandDupe implements Listener {
    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        if (!(Main.config.getBoolean("dupe.command.enable"))) return;
        if (!(event.getPlayer().hasPermission("anarchy.dupe.command"))) return;
        if (event.getMessage().startsWith("dupe")) {
            event.setCancelled(true);
            ItemStack item = event.getPlayer().getInventory().getItemInMainHand().clone();
            for (int i = 0; i < Main.config.getInt("dupe.command.times"); i++) {
                event.getPlayer().getInventory().addItem(item);
            }
        }
    }
}
