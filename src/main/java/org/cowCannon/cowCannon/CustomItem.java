package org.cowCannon.cowCannon;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collections;

public class CustomItem implements CommandExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
			@NotNull String[] args) {
		if (!(sender instanceof Player)){
			sender.sendMessage(ChatColor.RED + "That command only can be executed by a player");
			return true;
		}

		Player player = (Player) sender;
		ItemStack customBucket = new ItemStack(Material.BUCKET);
		ItemMeta meta = customBucket.getItemMeta();
		meta.setDisplayName(ChatColor.RED + "Exploder 3000");
		meta.setLore(Arrays.asList(ChatColor.GREEN + "Make any cow explode!", ChatColor.RED + "Be careful!!"));
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addEnchant(Enchantment.ARROW_INFINITE, 1, true); //add a decoration to the item
		meta.getPersistentDataContainer().set(Keys.CUSTOM_BUCKET, PersistentDataType.BOOLEAN, true);

		customBucket.setItemMeta(meta);

		player.getInventory().addItem(customBucket);
		return false;
	}
	
}
