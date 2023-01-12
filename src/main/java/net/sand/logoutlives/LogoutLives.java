package net.sand.logoutlives;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

import net.sand.logoutlives.listeners.*;
import net.sand.logoutlives.serializable.LogoutVillager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net.sand.logoutlives.util.SaveFilesLL;
import net.sand.logoutlives.util.TickChecker;

public class LogoutLives extends JavaPlugin {

	public static HashMap<UUID, LogoutVillager> villagersL = new HashMap<>();
	public static LogoutLives logoutL;
	public FileConfiguration config;
	
	// Fired when plugin is first enabled
	@Override
	public void onEnable() {
		logoutL = this;
		
		// Configurations
		this.saveDefaultConfig();
		config = this.getConfig();
		createConfig(config);
		config.options().copyDefaults(true);
		saveConfig();
		
		// Implement listeners
		PluginManager pm = getServer().getPluginManager();
		registerEvents(pm);

		// Create main folder
		createFolder();
		
		// Create save file for offlinePlayer
		SaveFilesLL.readLogoutVillagers("plugins/LogoutLives/offlinePlayers.data");
		
		TickChecker.scheduleTimer(this, this.getServer().getWorld("world"));

		// Finish
		getLogger().info("Initialized !");
	}

	// Fired when plugin is disabled
	@Override
	public void onDisable() {
		// Save files
		SaveFilesLL.saveLogoutVillagers(villagersL, "plugins/LogoutLives/offlinePlayers.data");
	}
	
	public void createFolder() {
		// Creates plugin main folder
		File directory = new File("plugins/LogoutLives");
		if (!directory.exists()) {
			if (directory.mkdir()) {
				getLogger().info("Directory created");
			} else {
				getLogger().warning("Directory not created");
			}
		}
	}
	
	public void createConfig(FileConfiguration config) {
		
		config.addDefault("enableTitle", true);
		config.addDefault("mainTitle", "F (offline)");
		config.addDefault("lightningDamage", "NO DAMAGE");
		config.addDefault("lightning", true);
		config.addDefault("enableSound", true);
		config.addDefault("canOpenDoors", true);
		config.addDefault("dropsInventory", true);
		config.addDefault("invulnerable", false);
		config.addDefault("chunksToLoad", 1);
	}

	public void registerEvents(PluginManager pm) {
		pm.registerEvents(new PlayerQuitServer(), this);
		pm.registerEvents(new PlayerJoinServer(), this);
		pm.registerEvents(new EntityKill(), this);
		// Enable this one if NPCs won't be able to open doors
		if (!logoutL.getConfig().getBoolean("canOpenDoors")) {
			pm.registerEvents(new EntityInteract(), this);
		}
		pm.registerEvents(new PlayerInteract(), this);
	}
	
	public static LogoutLives get() {
		return logoutL;
	}

}
