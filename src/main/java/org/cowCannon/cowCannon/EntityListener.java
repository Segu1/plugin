package org.cowCannon.cowCannon;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.EquipmentSlot;

public class EntityListener implements Listener {
    @EventHandler
    public void onEntityRightClick(PlayerInteractAtEntityEvent event) {
        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();

        // Check that the player used their main hand
        if (event.getHand() != EquipmentSlot.HAND) return;

        // Make sure the player is holding Flint
        if (player.getInventory().getItemInMainHand().getType() == Material.BUCKET) {
            // Check if the entity clicked is a Cow and has the "CowCannon" metadata
            if (entity.getType() == CowSettings.getInstance().getExplodingType() && entity.hasMetadata("CowCannon")) {
                // Create an explosion at the cow's location
                entity.getWorld().createExplosion(entity.getLocation(), 4F, false, false);
            }
        }
    }

    }