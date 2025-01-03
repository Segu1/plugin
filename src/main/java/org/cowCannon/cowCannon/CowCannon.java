package org.cowCannon.cowCannon;


import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.cowCannon.cowCannon.Main.CowSettings;
import org.cowCannon.cowCannon.Tasks.ButterflyTask;
import org.cowCannon.cowCannon.Tasks.PlayerMovementTask;
import org.cowCannon.cowCannon.commands.ButterflyCommand;
import org.cowCannon.cowCannon.commands.CowCommand;
import org.cowCannon.cowCannon.customItems.CustomItem;
import org.cowCannon.cowCannon.listeners.ChatListener;
import org.cowCannon.cowCannon.listeners.EntityListener;
import org.cowCannon.cowCannon.listeners.LaserTagListener;
import org.cowCannon.cowCannon.listeners.PlayerJoinListener;
import org.cowCannon.cowCannon.recipes.CustomRecipes;

import java.util.Objects;

public final class CowCannon extends JavaPlugin {
	private BukkitTask task;
	private BukkitTask task2;
	private BukkitTask task3;

	public static Plugin getInstance() {
		return getPlugin(CowCannon.class);
	}

	public void onEnable() {
		// Plugin startup logic
		getLogger().info("CowCannon has been enabled");

		// Register events
		try {
			getServer().getPluginManager().registerEvents(new EntityListener(), this);
			getServer().getPluginManager().registerEvents(new ChatListener(), this);
			getServer().getPluginManager().registerEvents(new LaserTagListener(), this);
			getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
		    CustomRecipes.register();
		    
		} catch (Exception e) {
			getLogger().severe("Failed to register events: " + e.getMessage());
		}
		 //load commands from yml
		try{
		Objects.requireNonNull(getCommand("cow")).setExecutor(new CowCommand());
		Objects.requireNonNull(getCommand("butterfly")).setExecutor(new ButterflyCommand());
		Objects.requireNonNull(getCommand("customItem")).setExecutor(new CustomItem());}
		catch (NullPointerException e) {
			getLogger().severe("Failed to register commands: " + e.getMessage());
		}
		// cargar el archivo de configuracion:
		/*getCommand("gui").setExecutor(new GuiCommand());*/
		
		
		//load settings from settings.yml
		CowSettings.getInstance().load();
		
		//create autotasks
		task = getServer().getScheduler().runTaskTimer(this, ButterflyTask.getInstance(), 0, 1);
		task2 = getServer().getScheduler().runTaskTimer(this, Board.getInstance(), 0, 20); // 20 equal
		// s 1 second
		task3 = getServer().getScheduler().runTaskTimer(this, PlayerMovementTask.getInstance(), 0, 5);
	}

	public void onDisable() {
		if (task != null && !task.isCancelled()) {
			task.cancel();
		}
		
		if(task2 != null && !task.isCancelled()) {
			task2.cancel();
		}

		if(task3 != null && !task.isCancelled()) {
			task3.cancel();
		}
		
		
		// Plugin shutdown logic
		getLogger().info("CoCwannon has been disabled");
	}
}
