package org.cowCannon.cowCannon.listeners;

import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.cowCannon.cowCannon.Main.TitleCreator;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        TitleCreator titleCreator = new TitleCreator("Welcome to the server " + player.getName(),
                "We hope you have a great time here!");
        titleCreator.showMyTitle(player, NamedTextColor.AQUA);
    }
}
