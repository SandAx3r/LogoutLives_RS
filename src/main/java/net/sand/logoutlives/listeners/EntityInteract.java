package net.sand.logoutlives.listeners;

import net.sand.logoutlives.LogoutLives;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityInteractEvent;

import java.util.UUID;

public class EntityInteract implements Listener {

    @EventHandler
    public void onEntityInteract(EntityInteractEvent e) {
        UUID uuid = e.getEntity().getUniqueId();

        if((e.getBlock().getType().toString().contains("DOOR")) && (LogoutLives.villagersL.containsKey(uuid))) {
            e.setCancelled(true);
        }
    }
}
