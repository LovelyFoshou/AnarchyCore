package me.foshou.plugins.anarchy.core.features.limits;

import me.foshou.plugins.anarchy.core.Main;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.HashMap;

public class CrystalSpeed implements Listener {


    public static HashMap<String, Long> breaking = new HashMap<>();


    @EventHandler
    public void onEnderCrystalBreak(EntityDamageByEntityEvent event) {
        if (!Main.config.getBoolean("limits.CrystalSpeed.enable")) return;
        if (event.getDamager().hasPermission("anarchy.limits.bypass")) return;
        // 检查被攻击的实体是否是末影水晶
        if (event.getEntity() instanceof EnderCrystal) {
            // 检查攻击者是否是玩家
            if (event.getDamager() instanceof Player) {
                Player player = (Player) event.getDamager();
                player.setPlayerListHeader("AnarchyCore 0.1.0");
                if (breaking.containsKey(player.getName())) {
                    if (System.currentTimeMillis() - breaking.get(event.getDamager().getName())< Main.config.getLong(
                            "limits.CrystalSpeed.max_period"
                    )) {
                        event.setCancelled(true);
                    }
                } else {
                    breaking.put(event.getDamager().getName(), System.currentTimeMillis());
                }
            }
        }
    }
}
