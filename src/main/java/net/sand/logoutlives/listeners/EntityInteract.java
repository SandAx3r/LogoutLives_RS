package net.sand.logoutlives.listeners;

import net.sand.logoutlives.LogoutLives;
import net.sand.logoutlives.LogoutVillager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityInteractEvent;

public class EntityInteract implements Listener {

    @EventHandler
    public void onEntityInteract(EntityInteractEvent e) {

        if(e.getBlock().getType().toString().contains("DOOR")) {
            for (LogoutVillager lv : LogoutLives.villagersL) {
                if(lv.getVillagerUUID().equals(e.getEntity().getUniqueId())) {
                    e.setCancelled(true);
                    break;
                }
            }
        }
    }
}
