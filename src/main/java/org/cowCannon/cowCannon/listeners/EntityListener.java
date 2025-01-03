package org.cowCannon.cowCannon.listeners;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.cowCannon.cowCannon.CowCannon;
import org.cowCannon.cowCannon.Main.CowSettings;
import org.cowCannon.cowCannon.Main.Keys;

public class EntityListener implements Listener {
    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent interaction) {
        // Ensure the event is triggered only for the main hand
        if (interaction.getHand() != EquipmentSlot.HAND) {
            return; // Ignore off-hand interactions
        }

        Player player = interaction.getPlayer();
        ItemMeta holdingItemMeta = player.getItemInHand().getItemMeta();

        if (holdingItemMeta == null) return;

        PersistentDataContainer holdingPersistentDataContainer = holdingItemMeta.getPersistentDataContainer();

        // Check if the item has the desired persistent data key and action is right-click
        if (holdingPersistentDataContainer.has(Keys.REPAIR_BOOK) && interaction.getAction().isRightClick()) {
            try {
                ItemStack itemToRepair = player.getInventory().getItem(0);
                if (itemToRepair == null || itemToRepair.getType() == Material.AIR) {
                    player.sendMessage(ChatColor.RED + "Not fixable!");
                    return;
                }
                repairItemPartially(itemToRepair, 200);
                setPlayerFood(player, 0.2F);
                player.sendMessage(ChatColor.GREEN + "Item fixed!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @EventHandler
    public void onEntityRightClick(PlayerInteractAtEntityEvent event) {
        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();
        ItemMeta holdingItemMeta = player.getItemInHand().getItemMeta();

        if (holdingItemMeta == null) return;

        PersistentDataContainer holdingPersistentDataContainer = holdingItemMeta.getPersistentDataContainer();

        if (holdingPersistentDataContainer.has(Keys.CUSTOM_BUCKET)) {
            try {
                PersistentDataContainer entityContainer = entity.getPersistentDataContainer();
                if (entity.getType() == CowSettings.getInstance().getExplodingType() && entityContainer.has(Keys.CUSTOM_COW)) {
                    entity.getWorld().createExplosion(entity.getLocation(), 4F, false, false);
                }
            } catch (Exception e) {
                System.out.println(ChatColor.RED + "Error: " + e.toString());
            }
        }
    }
    
    private void setPlayerFood(Player player, Float amountToReduce) {
		int foodLevel = player.getFoodLevel();
		int aux = Math.round(foodLevel - (foodLevel * amountToReduce));
		player.setFoodLevel(aux);
		return;
	}

	public void repairItemPartially(ItemStack item, int repairAmount) {
        short currentDurability = item.getDurability();
        short newDurability = (short) Math.max(0, currentDurability - repairAmount); // Ensure it's not negative
        item.setDurability(newDurability);
        return;
    }
    
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
    // Get the player and their location
    Location location = event.getPlayer().getLocation();
    World world = location.getWorld();

    if (world == null) return;

    // Spawn the firework at the player's location
    Firework firework = (Firework) world.spawn(location, Firework.class);

    // Customize the firework
    FireworkMeta fireworkMeta = firework.getFireworkMeta();
    fireworkMeta.addEffect(FireworkEffect.builder()
            .withColor(Color.RED, Color.BLUE, Color.YELLOW) // Firework colors
            .withFade(Color.ORANGE) // Fade color
            .with(FireworkEffect.Type.BALL_LARGE) // Firework shape
            .trail(true) // Add a trail
            .flicker(true) // Add flicker effect
            .build());
    fireworkMeta.setPower(1); // Duration of the firework
    firework.setFireworkMeta(fireworkMeta);

    // Make the firework explode immediately
    Bukkit.getScheduler().runTaskLater(CowCannon.getInstance(), firework::detonate, 50); // Detonate after 1 tick
}}
    