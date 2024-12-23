package org.cowCannon.cowCannon;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public final class CowCannon extends JavaPlugin {
	private BukkitTask task;

	public static Plugin getInstance() {
		return getPlugin(CowCannon.class);
	}

	@Override
	public void onEnable() {
		// Plugin startup logic
		getLogger().info("CowCannon has been enabled");

		// Register events
		try {
			getServer().getPluginManager().registerEvents(new EntityListener(), this);
		} catch (Exception e) {
			getLogger().severe("Failed to register events: " + e.getMessage());
			e.printStackTrace();
		}

		// Register commands
		if (getCommand("cow") != null) {
			getCommand("cow").setExecutor(new CowCommand());
		} else {
			getLogger().severe("Command 'cow' is not defined in plugin.yml!");
		}

		getCommand("butterfly").setExecutor(new ButterflyCommand());
		getCommand("customItem").setExecutor(new CustomItem());		// cargar el archivo de configuracion:
		CowSettings.getInstance().load();
		task = getServer().getScheduler().runTaskTimer(this, ButterflyTask.getInstance(), 0, 1);
	}

	@Override
	public void onDisable() {
		if (task != null && !task.isCancelled()) {
			task.cancel();
		}
		// Plugin shutdown logic
		getLogger().info("CoCwannon has been disabled");
	}
}
