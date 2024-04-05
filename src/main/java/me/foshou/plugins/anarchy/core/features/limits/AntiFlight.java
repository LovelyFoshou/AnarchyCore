package me.foshou.plugins.anarchy.core.features.limits;


import me.foshou.plugins.anarchy.core.Main;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class AntiFlight implements Listener {


    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if(event.getPlayer().hasPermission("anarchy.limits.bypass")) return;
        if (!Main.config.getBoolean("limits.AntiFlight.enable")) return;
        Player player = event.getPlayer();
        // 获取玩家脚下的方块
        Block blockBelow = player.getLocation().getBlock().getRelative(0, -1, 0);

        // 检查玩家脚下是否是空气
        if (blockBelow.getType() == Material.AIR && !player.getInventory().getChestplate().getType().equals(Material.ELYTRA)) {
            player.kickPlayer("Wrong moving!");
        }
    }
}
