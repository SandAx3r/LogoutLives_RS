package net.sand.logoutlives.listeners;

import net.sand.logoutlives.LogoutLives;
import net.sand.logoutlives.serializable.LogoutVillager;
import net.sand.logoutlives.util.SaveFilesLL;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;

public class InventoryGUI implements Listener {
    private Inventory inv;
    private LogoutVillager lv;
    private Player p;

    public InventoryGUI(Player p, LogoutVillager lv) {
        this.p = p;
        this.lv = lv;
        ExampleGui();
        Bukkit.getServer().getPluginManager().registerEvents(this, LogoutLives.get());
    }

    public void ExampleGui() {
        // Create a new inventory, with no owner (as this isn't a real inventory), a size of nine, called example
        inv = Bukkit.createInventory(null, 54, "Inventory of: " + lv.getPlayerName());

        // Put the items into the inventory
        initializeItems();
        // Open the inventory
        openInventory(p);
    }

    // You can call this whenever you want to put the items in
    public void initializeItems() {
        try {
            for (ItemStack is: SaveFilesLL.restoreInventory(lv.getPlayerName())) {
                if(is != null) {
                    inv.addItem(is);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void openInventory(HumanEntity ent) {
        ent.openInventory(inv);
    }

    // Cancel moving
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().equals(inv)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (event.getInventory().equals(inv)) {
            HandlerList.unregisterAll(this);
        }
    }
}
