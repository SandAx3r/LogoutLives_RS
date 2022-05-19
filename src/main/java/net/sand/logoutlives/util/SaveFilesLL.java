package net.sand.logoutlives.util;

import net.sand.logoutlives.LogoutLives;
import net.sand.logoutlives.serializable.LogoutVillager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class SaveFilesLL {

	// Save objects
	public static void saveLogoutVillagers(HashMap<UUID, LogoutVillager> villagers, String filepath) {
		try {

			// Save

			// First, create list for saving optm.
			List<LogoutVillager> villagerList = new ArrayList<>(villagers.values());

			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filepath));


			oos.writeObject(villagerList);

			oos.close();

			LogoutLives.get().getLogger().info("Successfully saved LogoutVillagers");

		} catch (Exception ex) {
			LogoutLives.get().getLogger().warning("An error occurred while saving LogoutVillagers");
			ex.printStackTrace();
		}
	}

	// Read objects
	public static void readLogoutVillagers(String filepath) {
		try {
			// Read
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filepath));

			for (LogoutVillager v: (List<LogoutVillager>) ois.readObject()) {
				LogoutLives.villagersL.put(v.getVillagerUUID(), v);
			}

			ois.close();
			LogoutLives.get().getLogger().info("Successfully read LogoutVillagers");
		} catch (FileNotFoundException ex) {
			LogoutLives.get().getLogger().info("LogoutVillagers file does not exist, skipping reading");
		} catch (Exception ex) {
			LogoutLives.get().getLogger().warning("An error occurred while reading LogoutVillagers");
			ex.printStackTrace();
		}
	}

	public static void saveInventory(Player p) throws IOException {
		File f = new File(LogoutLives.get().getDataFolder().getAbsolutePath(), p.getName() + ".yml");
		FileConfiguration c = YamlConfiguration.loadConfiguration(f);
		c.set("inventory.armor", p.getInventory().getArmorContents());
		c.set("inventory.content", p.getInventory().getContents());
		c.save(f);
	}

	@SuppressWarnings("unchecked")
	public static ItemStack[] restoreInventory(String p) throws IOException {
		File f = new File(LogoutLives.get().getDataFolder().getAbsolutePath(), p + ".yml");
		FileConfiguration c = YamlConfiguration.loadConfiguration(f);
		ItemStack[] content = ((List<ItemStack>) c.get("inventory.armor")).toArray(new ItemStack[0]);
		content = ((List<ItemStack>) c.get("inventory.content")).toArray(new ItemStack[0]);

		return content;
	}

}
