package org.cowCannon.cowCannon.commands;
/*
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class GuiCommand implements CommandExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
			@NotNull String[] args) {
		if(!(sender instanceof Player)){
			sender.sendMessage("Just player can execute that command");
			return true; 
		}
		
		
		Player player = (Player) sender; 
		new MainMenu().displayTo(player);
		
		/*Inventory inventory = Bukkit.createInventory(player, 27, ChatColor.GREEN + "" 
		+ ChatColor.BOLD + "Preferences Menu");
		
	
		
		ItemStack clearInventoryButton = new ItemStack(Material.REDSTONE);
		ItemMeta ClearInvMeta = clearInventoryButton.getItemMeta();
		ClearInvMeta.setDisplayName(ChatColor.RED + "Clear your inventory!");
		clearInventoryButton.setItemMeta(itemMeta);
		
		ItemStack clearWeatherButtonItemStack = new ItemStack(Material.EMERALD);
		ItemMeta meta = clearInventoryButton.getItemMeta(); 
		meta.setDisplayName(ChatColor.GREEN + "Clear the weather");
		clearWeatherButtonItemStack.setItemMeta(meta);
		
		inventory.setItem(11, getDiamondButton);
		inventory.setItem(13, clearInventoryButton);
		inventory.setItem(15, clearWeatherButtonItemStack);
		
		player.setMetadata("OpenedMenu", new FixedMetadataValue(CowCannon.getInstance(), inventory));
		player.openInventory(inventory);*/
		
		/*
		return true;
	
	}

	}*/


	
