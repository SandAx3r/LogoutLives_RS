package net.sand.logoutlives.listeners;

import net.sand.logoutlives.LogoutLives;
import net.sand.logoutlives.serializable.LogoutVillager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

public class PlayerInteract implements Listener {

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent e) {
        UUID uuid = e.getRightClicked().getUniqueId();
        Player p = e.getPlayer();

        if (LogoutLives.villagersL.containsKey(uuid)) {
            LogoutVillager lv = LogoutLives.villagersL.get(uuid);
            Inventory inv = p.getInventory();
            //TODO Inventario

            p.openInventory(inv);
        }
    }
}
