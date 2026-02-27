package com.vegetable.task;

import com.vegetable.rmi.Task;
import com.vegetable.rmi.VegetablePriceTable;

/**
 * Task to look up a vegetable price and calculate total cost based on quantity.
 */
public class CalVegetableCost implements Task<String> {

    private String vegetableName;
    private double quantity; // in kg

    public CalVegetableCost(String vegetableName, double quantity) {
        this.vegetableName = vegetableName;
        this.quantity = quantity;
    }

    @Override
    public String execute() {
        VegetablePriceTable table = VegetablePriceTable.getInstance();
        if (!table.exists(vegetableName)) {
            return "ERROR: " + vegetableName + " not found in price table.";
        }
        double price = table.getPrice(vegetableName);
        double totalCost = price * quantity;
        return String.format("Vegetable: %s | Price: KES %.2f/kg | Quantity: %.2f kg | Cost: KES %.2f",
                vegetableName, price, quantity, totalCost);
    }
}