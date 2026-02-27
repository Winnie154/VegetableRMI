package com.vegetable.task;

import com.vegetable.rmi.Task;
import com.vegetable.rmi.VegetablePriceTable;

/**
 * Task to update the price of an existing vegetable in the price table.
 */
public class UpdateVegetablePrice implements Task<String> {

    private String vegetableName;
    private double newPrice;

    public UpdateVegetablePrice(String vegetableName, double newPrice) {
        this.vegetableName = vegetableName;
        this.newPrice = newPrice;
    }

    @Override
    public String execute() {
        VegetablePriceTable table = VegetablePriceTable.getInstance();
        if (!table.exists(vegetableName)) {
            return "ERROR: " + vegetableName + " not found in price table.";
        }
        double oldPrice = table.getPrice(vegetableName);
        table.updateVegetable(vegetableName, newPrice);
        return "SUCCESS: Updated " + vegetableName + " from KES " + oldPrice + " to KES " + newPrice + " per kg.";
    }
}