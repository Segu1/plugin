package org.cowCannon.cowCannon.listeners;

import de.slikey.effectlib.EffectManager;
import de.slikey.effectlib.effect.AnimatedBallEffect;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.RayTraceResult;
import org.cowCannon.cowCannon.CowCannon;
import org.cowCannon.cowCannon.Main.Keys;

public class LaserTagListener implements Listener {
    private final EffectManager effectManager = new EffectManager(CowCannon.getInstance());

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if(event.getAction() != Action.RIGHT_CLICK_AIR) {
            return;
        }
        Player player = event.getPlayer();
        ItemStack handItem = player.getInventory().getItemInMainHand();
        if(handItem.hasItemMeta() && handItem.getItemMeta().getPersistentDataContainer().has(Keys.LASER_TAG, PersistentDataType.BOOLEAN)) {
            RayTraceResult rayTraceResult = player.rayTraceBlocks(50);
            if(rayTraceResult != null && rayTraceResult.getHitBlock() != null && rayTraceResult.getHitBlock().isSolid()){
               // player.getWorld().createExplosion(rayTraceResult.getHitBlock().getLocation(), 2F, false, true);
                AnimatedBallEffect animatedBallEffect = new AnimatedBallEffect(effectManager);
                animatedBallEffect.setLocation(rayTraceResult.getHitBlock().getLocation());
                animatedBallEffect.start();

                new BukkitRunnable(){
                    @Override
                    public void run() {
                        animatedBallEffect.cancel();
                    }
                }.runTaskLater(CowCannon.getInstance(), 20*2);
            }

            else {
                player.sendMessage(Component.text("[Laser] " + "Objective too far or not solid!")
                        .color(NamedTextColor.DARK_RED));
            }
        }
        else{
            Bukkit.getLogger().info("Eror, metadata not reached");
        }


    };
    }

