package org.cowCannon.cowCannon;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;
import org.bukkit.util.Transformation;
import org.jetbrains.annotations.NotNull;

public class DisplayEntityCommand implements CommandExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
			@NotNull String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Only players can execute this command!");
			return true;
		}
		;
		Player player = (Player) sender;
		try {
			TextDisplay text = player.getWorld().spawn(player.getLocation(), TextDisplay.class);
			Transformation transformation = text.getTransformation();
			transformation.getScale().set(20);
			text.setTransformation(transformation);

		} catch (Exception e) {
		}

		return false;
	}

}
