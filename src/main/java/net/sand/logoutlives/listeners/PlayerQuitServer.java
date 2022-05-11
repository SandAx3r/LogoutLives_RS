package net.sand.logoutlives.listeners;

import net.sand.logoutlives.LogoutLives;
import net.sand.logoutlives.util.SaveFilesLL;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import net.sand.logoutlives.serializable.LogoutVillager;

import java.io.IOException;

public class PlayerQuitServer implements Listener {

	// Player Logout
	@EventHandler
	public void onPlayerLogout(PlayerQuitEvent e) {
		Player p = e.getPlayer();

		if (p.getGameMode() != GameMode.SPECTATOR) {
			LogoutVillager lv = new LogoutVillager(p.getDisplayName(), p.getUniqueId() ,false);
			try {
				SaveFilesLL.saveInventory(p);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			lv.create(p.getLocation());

			// Save villager
			LogoutLives.villagersL.put(lv.getVillagerUUID(), lv);
		}
		
	}

}
