package net.sand.logoutlives;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

public class LogoutVillager implements Serializable {

	/**
	 * 
	 */
	public String playerName;
	public UUID villagerUUID;
	public Boolean dead;
	
	// Location
	public String world;
	public double x;
	public double y;
	public double z;

	public LogoutVillager(String playerName, Boolean dead) {
		this.playerName = playerName;
		this.dead = dead;
	}

	// Other methods

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public UUID getVillagerUUID() {
		return this.villagerUUID;
	}

	public double getVillagerX() {
		return this.x;
	}

	public double getVillagerY() {
		return this.y;
	}

	public double getVillagerZ() {
		return this.z;
	}
	
	public String getWorld() {
		return this.world;
	}

	public void setVillagerLocation(Location loc) {
		this.x = loc.getX();
		this.y = loc.getY();
		this.z = loc.getZ();
		this.world = Objects.requireNonNull(loc.getWorld()).getName();
	}

	public Boolean isDead() {
		return dead;
	}

	public void setDead(Boolean dead) {
		this.dead = dead;
	}

	@Override
	public String toString() {
		return "LogoutVillager [playerName=" + playerName + ", dead=" + dead + "]";
	}

	public Entity create(Location loc) {
		/**
		 *
		 * VILLAGER CREATION
		 *
		 **/
		Entity villager = Objects.requireNonNull(Bukkit.getWorld(Objects.requireNonNull(loc.getWorld()).getName())).spawnEntity(loc, EntityType.VILLAGER);
		villager.setCustomName(this.playerName);
		villager.setCustomNameVisible(true);
		villager.setPersistent(true);

		this.villagerUUID = villager.getUniqueId();
		this.x = villager.getLocation().getX();
		this.y = villager.getLocation().getY();
		this.z = villager.getLocation().getZ();
		this.world = Objects.requireNonNull(villager.getLocation().getWorld()).getName();

		return villager;
	}

}
