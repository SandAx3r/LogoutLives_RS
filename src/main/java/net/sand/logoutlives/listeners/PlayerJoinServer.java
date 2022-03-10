package net.sand.logoutlives.listeners;

import net.sand.logoutlives.LogoutLives;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.sand.logoutlives.LogoutVillager;

public class PlayerJoinServer implements Listener{

	LogoutLives logoutL = LogoutLives.get();
	
	// Player Login
	@EventHandler
	public void onPlayerLogin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		Entity v;
		p.getWorld().loadChunk(p.getLocation().getChunk());

		// Find the villager in list
		for (LogoutVillager lv : LogoutLives.villagersL) {

			if (p.getDisplayName().equals(lv.getPlayerName())) {
				v = (Entity) logoutL.getServer().getEntity(lv.getVillagerUUID());
				
				logoutL.getServer().getEntity(lv.getVillagerUUID());
				Location loc = new Location(logoutL.getServer().getWorld(lv.getWorld()), lv.getVillagerX(), lv.getVillagerY(), lv.getVillagerZ());
				System.out.println(logoutL.getServer().getWorld(lv.getWorld()));
				if (lv.isDead()) {
					p.teleport(loc);
					System.out.println("[LogoutLives] " + p.getDisplayName() + " died offline, now online");
					p.setHealth(0);
					LogoutLives.villagersL.remove(lv);
					return;
				}
				
				// Remove villager from saveTXT
				LogoutLives.villagersL.remove(lv);
				
				// First remove to prevent errors
				v.remove();
				p.teleport(v);
				System.out.println("[LogoutLives] Entity villager found, name= " + lv.getPlayerName());
				

				System.out.println("[LogoutLives] LogoutEntity removed: " + lv.getPlayerName());

				return;
			}
		}

		System.out.println("[LogoutLives] There are not living entities called " + p.getDisplayName());
	}	
	
}
