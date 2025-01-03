package org.cowCannon.cowCannon.Tasks;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class LaserTagTask implements Runnable {
    private Player online;

    private static final LaserTagTask instance = new LaserTagTask();

    private LaserTagTask(){};

    public void setPlayer(Player player){
        this.online = player;
    }

    public static LaserTagTask getInstance(){
        return instance;
    }

    @Override
    public void run() {
        if(this.online == null) return;
        int length = 16;
        double particleDistance = 0.3;
                Location location = online.getLocation().add(0, 1, 0);
                for (double waypoint = 1; waypoint <= length; waypoint += particleDistance) {
                    Vector vector = location.getDirection().multiply(waypoint);
                    Location stepLocation = location.clone().add(vector);
                    if (stepLocation.getBlock().getType() != Material.AIR) {
                        break;
                    }

                    stepLocation.getWorld().spawnParticle(
                            Particle.REDSTONE,
                            stepLocation,
                            1,
                            new Particle.DustOptions(Color.RED, 0.75F) // Particle color and size
                    );
                }
        }
    }




