package com.vegetable.rmi;

import java.util.HashMap;
import java.util.Map;

/**
 * Singleton in-memory vegetable price table.
 * Stores vegetable names mapped to their prices.
 */
public class VegetablePriceTable {

    private static VegetablePriceTable instance;
    private Map<String, Double> priceTable;

    private VegetablePriceTable() {
        priceTable = new HashMap<>();
        // Seed some default vegetables
        priceTable.put("Tomato", 50.0);
        priceTable.put("Kale", 30.0);
        priceTable.put("Carrot", 40.0);
        priceTable.put("Onion", 25.0);
        priceTable.put("Spinach", 20.0);
    }

    public static synchronized VegetablePriceTable getInstance() {
        if (instance == null) {
            instance = new VegetablePriceTable();
        }
        return instance;
    }

    public void addVegetable(String name, double price) {
        priceTable.put(name, price);
    }

    public void updateVegetable(String name, double price) {
        priceTable.put(name, price);
    }

    public void deleteVegetable(String name) {
        priceTable.remove(name);
    }

    public Double getPrice(String name) {
        return priceTable.get(name);
    }

    public Map<String, Double> getAllVegetables() {
        return priceTable;
    }

    public boolean exists(String name) {
        return priceTable.containsKey(name);
    }
}