package dev.cassiomartim.utils;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class InventoryUtils {

    private Inventory inventory;
    private String name;

    public InventoryUtils(String name, int size) {
        this.name = name;
        this.inventory = Bukkit.createInventory(null, size, name);
    }

    public InventoryUtils(String name, InventoryType type) {
        this.name = name;
        this.inventory = Bukkit.createInventory(null, type, name);
    }

    public String getName() {
        return name;
    }

    public Inventory getInventory() {
        return inventory;
    }

}
