package net.sand.logoutlives.listeners;

import net.sand.logoutlives.LogoutLives;
import net.sand.logoutlives.serializable.LogoutVillager;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinServer implements Listener{

	LogoutLives logoutL = LogoutLives.get();
	
	// Player Login
	@EventHandler
	public void onPlayerLogin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		Entity v;
		p.getWorld().loadChunk(p.getLocation().getChunk());

		// Find the villager in list
		for (LogoutVillager lv : LogoutLives.villagersL.values()) {

			if (p.getUniqueId().equals(lv.getPlayerUUID())) {
				v = logoutL.getServer().getEntity(lv.getVillagerUUID());

				Location loc = new Location(logoutL.getServer().getWorld(lv.getWorld()), lv.getVillagerX(), lv.getVillagerY(), lv.getVillagerZ());
				if (lv.isDead()) {
					if (logoutL.getConfig().getBoolean("dropsInventory")) {
						p.getInventory().clear();
					}
					p.teleport(loc);
					logoutL.getLogger().info(p.getDisplayName() + " died offline, now online");
					p.setHealth(0);
					LogoutLives.villagersL.remove(lv.getVillagerUUID());
					return;
				}
				
				// Remove villager from saved data
				LogoutLives.villagersL.remove(lv.getVillagerUUID());
				
				// First remove to prevent errors
				v.remove();
				p.teleport(v);

				logoutL.getLogger().info("Entity villager found, name= " + lv.getPlayerName());
				logoutL.getLogger().info("Entity removed: " + lv.getPlayerName());

				return;
			}
		}

		logoutL.getLogger().info("There are not living entities called " + p.getDisplayName());
	}	
	
}
