package org.cowCannon.cowCannon.customItems;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.cowCannon.cowCannon.Main.Keys;

import java.util.Arrays;

public class ItemFactory {
	
	private static ItemFactory instance;
	
	private ItemFactory() {};
	
	public static ItemFactory getInstance() {
		if(instance == null){
			instance = new ItemFactory();}
		
		return instance;
	};
	
	
	public ItemStack createRepairBook(int level) {
        // Crear el ítem (libro)
        ItemStack bookItemStack = new ItemStack(Material.BOOK);
        ItemMeta bookItemMeta = bookItemStack.getItemMeta();

        if (bookItemMeta != null) {
            // Añadir encantamiento visual (sin efectos reales)
            bookItemMeta.addEnchant(Enchantment.PROTECTION_FIRE, 1, true);
            bookItemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

            // Cambiar nombre del ítem
            bookItemMeta.setDisplayName(ChatColor.DARK_PURPLE + "Repair Book");

            // Añadir lore (descripción)
            bookItemMeta.setLore(Arrays.asList(
                    ChatColor.RED + "Custom Item",
                    "",
                    ChatColor.DARK_GREEN + "Use me to repair something, I will take just some food!", 
                    ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "| Tier: Rare |"
            ));

            // Añadir datos persistentes (marca única para identificar el libro)
            bookItemMeta.getPersistentDataContainer().set(Keys.REPAIR_BOOK, PersistentDataType.INTEGER, level);

            // Asignar los cambios al ítem
            bookItemStack.setItemMeta(bookItemMeta);
        }

        return bookItemStack; // Devolver el ítem configurado
    }

    public ItemStack createLaser() {
        ItemStack laserStack = new ItemStack(Material.NETHER_STAR);
        ItemMeta laserItemMeta = laserStack.getItemMeta();
        laserItemMeta.setDisplayName(ChatColor.DARK_RED + "Laser Tag");
        laserItemMeta.setLore(Arrays.asList("Custom Item", "", ChatColor.GOLD + "Fire-Gun to destroy your enemies"));
        laserItemMeta.getPersistentDataContainer().set(Keys.LASER_TAG, PersistentDataType.BOOLEAN, true);
        laserStack.setItemMeta(laserItemMeta);
        return laserStack;
    };

}
