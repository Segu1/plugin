package org.cowCannon.cowCannon;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ButterflyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("&cOnly players can use this command");
            return true;
        }

        Player player = (Player) sender;
        ButterflyTask butterflyTask = ButterflyTask.getInstance();

        if(butterflyTask.hasPlayer(player.getUniqueId())){
            butterflyTask.removePlayer(player.getUniqueId());
            player.sendMessage("&cYou are not seeing butterfly wings anymore!");
        } else {
            butterflyTask.addPlayer(player.getUniqueId());
            player.sendMessage("&aYou are now seeing butterfly wings!");
        }
        return true;
    }
}
