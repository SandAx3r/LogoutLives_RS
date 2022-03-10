package net.sand.logoutlives.extras;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import net.sand.logoutlives.LogoutLives;
import net.sand.logoutlives.LogoutVillager;

public class SaveFilesLL {

	// Save objects
	public static void saveLogoutVillagers(List<LogoutVillager> villagers, String filepath) {
		try {

			// Save

			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filepath));

			oos.writeObject(villagers);

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

			LogoutLives.villagersL = (List<LogoutVillager>) ois.readObject();

			ois.close();
			System.out.println("[LogoutLives] Successfully read LogoutVillagers");
		} catch (FileNotFoundException ex) {
			System.out.println("[LogoutLives] LogoutVillagers file does not exist, skipping reading");
			return;
		} catch (Exception ex) {
			System.out.println("[LogoutLives] An error occurred while reading LogoutVillagers");
			ex.printStackTrace();
		}
	}

}
