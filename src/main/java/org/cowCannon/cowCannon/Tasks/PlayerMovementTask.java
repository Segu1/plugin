package org.cowCannon.cowCannon.Tasks;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.cowCannon.cowCannon.CowCannon;
import org.cowCannon.cowCannon.Main.Keys;

import java.util.HashMap;
import java.util.UUID;

public class PlayerMovementTask implements Runnable {
    private final HashMap<UUID, Location> lastKnownLocations = new HashMap<>();
    private static PlayerMovementTask instance = new PlayerMovementTask();

    public static PlayerMovementTask getInstance() {
        return instance;
    };

    private PlayerMovementTask() {}


    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {

            if ( player == null ){continue;}

            ItemStack item = player.getInventory().getItemInMainHand();
            if(item.hasItemMeta() && !item.getItemMeta().getPersistentDataContainer().has(Keys.LASER_TAG,
                    PersistentDataType.BOOLEAN)){
                continue;
            }

            UUID playerId = player.getUniqueId();
            Location currentLocation = player.getLocation();
            LaserTagTask task = LaserTagTask.getInstance();
            task.setPlayer(player);
            // Obtén la última ubicación conocida del jugador
            Location lastLocation = lastKnownLocations.get(playerId);

            // Si no hay cambios en la posición, ejecuta la lógica de inactividad
            if (lastLocation != null && lastLocation.distanceSquared(currentLocation) < 0.01) {
                Bukkit.getScheduler().runTask(CowCannon.getInstance(), task);
            }

            // Actualiza la última ubicación conocida
            lastKnownLocations.put(playerId, currentLocation);
        }
    }
}
