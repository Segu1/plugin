package org.cowCannon.cowCannon.customItems;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.cowCannon.cowCannon.Main.Keys;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class CustomItem implements CommandExecutor {
	private static final ItemFactory itemFactory = ItemFactory.getInstance();

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
			@NotNull String[] args) {
		if(!(sender instanceof Player)){
			sender.sendMessage("Just player can execute that command");
			return false; 
		}
		
		String arg0 = args[0];

		Player player = (Player) sender;

		if(arg0.equalsIgnoreCase("BUCKET")) {
		ItemStack item = new ItemStack(Material.BUCKET);
		ItemMeta meta = item.getItemMeta();
		meta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);

		meta.displayName(Component.text("Bucket 3000").color(NamedTextColor.RED));
		meta.lore(Arrays.asList(Component.text("Some may say this make cows explode!").color(NamedTextColor.DARK_RED),
				Component.text("And they tell me I am crazy").color(NamedTextColor.DARK_RED)));

		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.getPersistentDataContainer().set(Keys.CUSTOM_BUCKET, PersistentDataType.BOOLEAN, true);
		item.setItemMeta(meta);
		player.getInventory().addItem(item);

		player.sendMessage(Component.text("Special bucket added to your inventory")
				.color(TextColor.color(NamedTextColor.AQUA))
				.decorate(TextDecoration.UNDERLINED)
						.clickEvent(ClickEvent.runCommand("/help"))
				.hoverEvent(HoverEvent.showText(Component.text("This bucket is useful to explode cows!")
						.color(NamedTextColor.RED))));
		return true;
		}
		
		if(arg0.equalsIgnoreCase("CUSTOM_BOOK_LEVEL_1")) {
			player.getInventory().addItem(itemFactory.createRepairBook(1));
			return true;
		}
		
		
		return false;
		
	}	
	
}
