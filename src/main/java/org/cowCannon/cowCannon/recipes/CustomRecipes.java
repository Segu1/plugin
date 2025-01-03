package org.cowCannon.cowCannon.recipes;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.cowCannon.cowCannon.CowCannon;
import org.cowCannon.cowCannon.customItems.ItemFactory;


public final class CustomRecipes {
	private static ItemFactory itemFactory =  ItemFactory.getInstance();
	
	public static void register(){
		ShapelessRecipe repairBookRec = new ShapelessRecipe(new NamespacedKey(CowCannon.getInstance(), "RepairBookRec"),
				itemFactory.createRepairBook(1));
		repairBookRec.addIngredient(3, Material.ANVIL);
		repairBookRec.addIngredient(1, Material.BOOK);
		Bukkit.addRecipe(repairBookRec);

		ShapedRecipe laserCraft = new ShapedRecipe(new NamespacedKey(CowCannon.getInstance(), "LasterTagRec"),
				itemFactory.createLaser());
		laserCraft.shape(" D ",
				"DBD",
				" D ");
		laserCraft.setIngredient('D', Material.TNT);
		laserCraft.setIngredient('B', Material.BOOK);
		Bukkit.addRecipe(laserCraft);

	}
}
