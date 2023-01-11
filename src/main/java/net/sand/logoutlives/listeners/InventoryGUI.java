package net.sand.logoutlives.listeners;

import net.sand.logoutlives.serializable.LogoutVillager;
import net.sand.logoutlives.util.SaveFilesLL;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;

public class InventoryGUI implements Listener {
    private Inventory inv;
    private LogoutVillager lv;

    public void ExampleGui(LogoutVillager lv) {
        this.lv = lv;
        // Create a new inventory, with no owner (as this isn't a real inventory), a size of nine, called example
        inv = Bukkit.createInventory(null, 54, "Inventory of: " + lv.getPlayerName());

        // Put the items into the inventory
        initializeItems();
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

    // You can open the inventory with this
    public void openInventory(final HumanEntity ent) {
        ent.openInventory(inv);
    }

    // Cancel dragging in our inventory
    @EventHandler
    public void onInventoryClick(final InventoryMoveItemEvent e) {
        System.out.println("DRAG");

    }
}
