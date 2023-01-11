package net.sand.logoutlives.listeners;

import net.sand.logoutlives.LogoutLives;
import net.sand.logoutlives.serializable.LogoutVillager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.yaml.snakeyaml.LoaderOptions;

import java.util.UUID;

public class PlayerInteract implements Listener {

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent e) {
        UUID uuid = e.getRightClicked().getUniqueId();
        Player p = e.getPlayer();

        if (LogoutLives.villagersL.containsKey(uuid)) {
            LogoutVillager lv = LogoutLives.villagersL.get(uuid);

            //TODO Inventario
            InventoryGUI lvInv = new InventoryGUI();
            lvInv.ExampleGui(lv);
            lvInv.openInventory(p);
        }
    }
}
