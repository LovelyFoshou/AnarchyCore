package me.foshou.plugins.anarchy.core.features.dupe;

import me.foshou.plugins.anarchy.core.Main;
import org.bukkit.block.ShulkerBox;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

import java.util.HashMap;

public class ShulkerDupe implements Listener {

    public static HashMap<String, Integer> map = new HashMap<>();

    @EventHandler
    public void onMine(BlockBreakEvent event) {
        if (!Main.config.getBoolean("dupe.mine_and_place.enable")) return;
        if (!event.getPlayer().hasPermission("anarchy.dupe.shulker")) return;
        if(!event.getBlock().getType().toString().toLowerCase().contains("shulker_box")) return;

        if (map.containsKey(event.getPlayer().getName())) {
            map.put(event.getPlayer().getName(), 1);
        } else {
            map.replace(event.getPlayer().getName(), map.get(event.getPlayer().getName())+1);
            if (map.get(event.getPlayer().getName())>=Main.config.getInt("dupe.mine_and_place.amount")) {
                ShulkerBox shulkerBox = (ShulkerBox) event.getBlock().getState();
                ItemStack shulkerItem = new ItemStack(event.getBlock().getType());
                BlockStateMeta blockStateMeta = (BlockStateMeta) shulkerItem.getItemMeta();
                if (blockStateMeta != null) {
                    blockStateMeta.setBlockState(shulkerBox);
                }
                shulkerItem.setItemMeta(blockStateMeta);
                shulkerBox.getWorld().dropItem(shulkerBox.getLocation(), shulkerItem);
            }
        }

    }
}
