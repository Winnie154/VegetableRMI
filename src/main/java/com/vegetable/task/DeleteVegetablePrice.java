package com.vegetable.task;

import com.vegetable.rmi.Task;
import com.vegetable.rmi.VegetablePriceTable;

/**
 * Task to delete a vegetable entry from the price table.
 */
public class DeleteVegetablePrice implements Task<String> {

    private String vegetableName;

    public DeleteVegetablePrice(String vegetableName) {
        this.vegetableName = vegetableName;
    }

    @Override
    public String execute() {
        VegetablePriceTable table = VegetablePriceTable.getInstance();
        if (!table.exists(vegetableName)) {
            return "ERROR: " + vegetableName + " not found in price table.";
        }
        table.deleteVegetable(vegetableName);
        return "SUCCESS: Deleted " + vegetableName + " from the price table.";
    }
}