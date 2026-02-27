package com.vegetable.task;

import com.vegetable.rmi.Task;
import com.vegetable.rmi.VegetablePriceTable;

/**
 * Task to add a new vegetable and its price to the price table.
 */
public class AddVegetablePrice implements Task<String> {

    private String vegetableName;
    private double price;

    public AddVegetablePrice(String vegetableName, double price) {
        this.vegetableName = vegetableName;
        this.price = price;
    }

    @Override
    public String execute() {
        VegetablePriceTable table = VegetablePriceTable.getInstance();
        if (table.exists(vegetableName)) {
            return "ERROR: " + vegetableName + " already exists. Use Update instead.";
        }
        table.addVegetable(vegetableName, price);
        return "SUCCESS: Added " + vegetableName + " at KES " + price + " per kg.";
    }
}