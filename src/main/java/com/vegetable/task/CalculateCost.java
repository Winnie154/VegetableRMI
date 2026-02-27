package com.vegetable.task;

import com.vegetable.rmi.Task;
import com.vegetable.rmi.VegetablePriceTable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * Task to generate a full purchase receipt showing cost, amount given,
 * change due, and the cashier name.
 */
public class CalculateCost implements Task<String> {

    private String cashierName;
    private Map<String, Double> order; // vegetable name -> quantity in kg
    private double amountGiven;

    public CalculateCost(String cashierName, Map<String, Double> order, double amountGiven) {
        this.cashierName = cashierName;
        this.order = order;
        this.amountGiven = amountGiven;
    }

    @Override
    public String execute() {
        VegetablePriceTable table = VegetablePriceTable.getInstance();
        StringBuilder receipt = new StringBuilder();
        String line = "========================================\n";
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

        receipt.append(line);
        receipt.append("       VEGETABLE STORE RECEIPT\n");
        receipt.append(line);
        receipt.append("Date     : ").append(date).append("\n");
        receipt.append("Cashier  : ").append(cashierName).append("\n");
        receipt.append(line);
        receipt.append(String.format("%-20s %8s %10s%n", "Item", "Qty(kg)", "Cost(KES)"));
        receipt.append("----------------------------------------\n");

        double totalCost = 0.0;
        for (Map.Entry<String, Double> entry : order.entrySet()) {
            String veg = entry.getKey();
            double qty = entry.getValue();
            Double price = table.getPrice(veg);
            if (price == null) {
                receipt.append(String.format("%-20s %8.2f   NOT FOUND%n", veg, qty));
            } else {
                double cost = price * qty;
                totalCost += cost;
                receipt.append(String.format("%-20s %8.2f %10.2f%n", veg, qty, cost));
            }
        }

        double change = amountGiven - totalCost;
        receipt.append("----------------------------------------\n");
        receipt.append(String.format("%-20s %19.2f%n", "TOTAL COST (KES):", totalCost));
        receipt.append(String.format("%-20s %19.2f%n", "AMOUNT GIVEN (KES):", amountGiven));
        receipt.append(String.format("%-20s %19.2f%n", "CHANGE DUE (KES):", change));
        receipt.append(line);
        receipt.append("       Thank you for shopping with us!\n");
        receipt.append(line);

        return receipt.toString();
    }
}