package net.sand.logoutlives.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import net.sand.logoutlives.LogoutLives;
import net.sand.logoutlives.LogoutVillager;

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

			System.out.println("[LogoutLives] Successfully saved LogoutVillagers");

		} catch (Exception ex) {
			System.out.println("[LogoutLives] An error occurred while saving LogoutVillagers");
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
			System.out.println("[LogoutLives] Successfully read LogoutVillagers");
		} catch (FileNotFoundException ex) {
			System.out.println("[LogoutLives] LogoutVillagers file does not exist, skipping reading");
		} catch (Exception ex) {
			System.out.println("[LogoutLives] An error occurred while reading LogoutVillagers");
			ex.printStackTrace();
		}
	}

}
