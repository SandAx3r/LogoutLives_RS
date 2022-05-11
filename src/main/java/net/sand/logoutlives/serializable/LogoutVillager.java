package net.sand.logoutlives.serializable;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class LogoutVillager implements Serializable {

	// Settings
	public String playerName;
	public UUID playerUUID;
	public UUID villagerUUID;
	public Boolean dead;
	
	// Location
	public String world;
	public double x;
	public double y;
	public double z;

	public LogoutVillager(String playerName, UUID playerUUID, Boolean dead) {
		this.playerName = playerName;
		this.playerUUID = playerUUID;
		// TODO Usar serialize() y deserialize() de ItemStack
		this.dead = dead;
	}

	// Other methods

	public String getPlayerName() {
		return playerName;
	}

	public UUID getPlayerUUID() { return playerUUID; }

	public UUID getVillagerUUID() {
		return this.villagerUUID;
	}

	public double getVillagerX() {return this.x;}
	public double getVillagerY() {
		return this.y;
	}
	public double getVillagerZ() {
		return this.z;
	}
	public String getWorld() {
		return this.world;
	}

	public Boolean isDead() {return dead;}
	public void setDead(Boolean dead) {this.dead = dead;}

	public void setVillagerLocation(Location loc) {
		this.x = loc.getX();
		this.y = loc.getY();
		this.z = loc.getZ();
		this.world = Objects.requireNonNull(loc.getWorld()).getName();
	}

	@Override
	public String toString() {
		return "LogoutVillager [playerName=" + playerName + ", dead=" + dead + "]";
	}

	public void create(Location loc) {
		Entity villager = Objects.requireNonNull(Bukkit.getWorld(Objects.requireNonNull(loc.getWorld()).getName())).spawnEntity(loc, EntityType.VILLAGER);
		villager.setCustomName(this.playerName);
		villager.setCustomNameVisible(true);
		villager.setPersistent(true);

		this.villagerUUID = villager.getUniqueId();
		this.setVillagerLocation(villager.getLocation());

	}

}
