package net.sand.logoutlives.listeners;

import net.sand.logoutlives.LogoutLives;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import net.sand.logoutlives.LogoutVillager;

public class PlayerQuitServer implements Listener {

	LogoutLives logoutL = LogoutLives.get();

	// Player Logout
	@EventHandler
	public void onPlayerLogout(PlayerQuitEvent e) {
		Player p = e.getPlayer();

		/**
		 * 
		 * VILLAGER CREATION
		 * 
		 **/
		if (p.getGameMode() != GameMode.SPECTATOR) {
			LogoutVillager lv = new LogoutVillager(p.getDisplayName(), false);
			lv.create(p.getLocation());

			// Save villager
			LogoutLives.villagersL.add(lv);
		}
		
	}

}
