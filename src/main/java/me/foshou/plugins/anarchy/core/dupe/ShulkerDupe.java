package me.foshou.plugins.anarchy.core.dupe;

import me.foshou.plugins.anarchy.core.Main;
import org.bukkit.Material;
import org.bukkit.block.ShulkerBox;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

import java.util.HashMap;

public class ShulkerDupe implements Listener {

    public static HashMap<String, Integer> map = new HashMap<>();

    @EventHandler
    public void onMine(BlockBreakEvent event) {
        if (!Main.config.getBoolean("dupe.mine_and_place.enable")) return;
        if (!event.getPlayer().hasPermission("anarchy.dupe.shulker")) return;
        switch (event.getBlock().getType()) {
            case SHULKER_BOX:
            case BLACK_SHULKER_BOX:
            case BROWN_SHULKER_BOX:
            case BLUE_SHULKER_BOX:
            case CYAN_SHULKER_BOX:
            case GRAY_SHULKER_BOX:
            case RED_SHULKER_BOX:
            case LIME_SHULKER_BOX:
            case PINK_SHULKER_BOX:
            case GREEN_SHULKER_BOX:
            case WHITE_SHULKER_BOX:
            case ORANGE_SHULKER_BOX:
            case PURPLE_SHULKER_BOX:
            case YELLOW_SHULKER_BOX:
            case MAGENTA_SHULKER_BOX:
            case LIGHT_BLUE_SHULKER_BOX:
            case LIGHT_GRAY_SHULKER_BOX:
                break;

            default:
                return;
        }

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
