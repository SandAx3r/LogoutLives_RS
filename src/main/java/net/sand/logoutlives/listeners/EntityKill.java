package net.sand.logoutlives.listeners;

import net.sand.logoutlives.LogoutLives;
import net.sand.logoutlives.util.SaveFilesLL;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import net.sand.logoutlives.serializable.LogoutVillager;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

public class EntityKill implements Listener {

	LogoutLives logoutL = LogoutLives.get();

	// Entity death
	@EventHandler
	public void onEntityDeath(EntityDeathEvent e) {
		Villager ent;
		UUID villagerUUID;

		if (e.getEntity() instanceof Villager) {
			ent = (Villager) e.getEntity();
			villagerUUID = ent.getUniqueId();
		} else {
			return;
		}

		if (LogoutLives.villagersL.containsKey(villagerUUID))
		{
			LogoutVillager lv = LogoutLives.villagersL.get(villagerUUID);

			logoutL.getLogger().info("An entity called: " + ent.getCustomName() + " died while OFFLINE");
			lv.setDead(true);
			lv.setVillagerLocation(ent.getLocation());

			// THUNDERSTRIKE
			if (logoutL.getConfig().getBoolean("lightning")) {
				if (Objects.equals(logoutL.getConfig().getString("lightningDamage"), "DAMAGE")) {
					ent.getWorld().strikeLightning(ent.getLocation()); // DAMAGE
				} else {
					ent.getWorld().strikeLightningEffect(ent.getLocation()); // NO DAMAGE
				}
			}

			// MESSAGE + SOUND + TITLE
			logoutL.getServer().broadcastMessage(ChatColor.GOLD + lv.getPlayerName() + ChatColor.WHITE
					+ " died while " + ChatColor.DARK_PURPLE + "offline");

			if (logoutL.getConfig().getBoolean("enableTitle")) {
				for (Player p : logoutL.getServer().getOnlinePlayers()) {
					if (logoutL.getConfig().getBoolean("enableSound")) {
						p.playSound(p.getLocation(), Sound.ENTITY_GHAST_HURT, 1, 1);
					}
					String title = logoutL.getConfig().getString("mainTitle");
					p.sendTitle(ChatColor.LIGHT_PURPLE + title, lv.getPlayerName(), 10, 70, 20); // Los
					// números
					// son
					// los
					// estandar

				}
			}

			// Drop inventory
			if(logoutL.getConfig().getBoolean("dropsInventory")) {
				try {
					for (ItemStack is: SaveFilesLL.restoreInventory(lv.getPlayerName())) {
						if(is != null) {
							ent.getWorld().dropItemNaturally(ent.getLocation(), is);
						}
					}
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}

	}
}
