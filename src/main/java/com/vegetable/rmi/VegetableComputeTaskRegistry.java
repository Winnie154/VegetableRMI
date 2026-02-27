package com.vegetable.rmi;

import com.vegetable.task.*;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * RMI Client - connects to VegetableComputeEngine and sends tasks.
 * Acts as the main client program / task registry.
 */
public class VegetableComputeTaskRegistry {

    public static void main(String[] args) {
        try {
            // Connect to RMI registry on localhost port 1099
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            Compute engine = (Compute) registry.lookup("VegetableComputeEngine");

            Scanner scanner = new Scanner(System.in);
            boolean running = true;

            System.out.println("=== Vegetable Service Client ===");

            while (running) {
                System.out.println("\nSelect a task:");
                System.out.println("1. Add vegetable price");
                System.out.println("2. Update vegetable price");
                System.out.println("3. Delete vegetable price");
                System.out.println("4. Calculate vegetable cost");
                System.out.println("5. Generate purchase receipt");
                System.out.println("6. Exit");
                System.out.print("Choice: ");

                int choice = Integer.parseInt(scanner.nextLine().trim());

                switch (choice) {
                    case 1: {
                        System.out.print("Vegetable name: ");
                        String name = scanner.nextLine();
                        System.out.print("Price per kg (KES): ");
                        double price = Double.parseDouble(scanner.nextLine());
                        String result = engine.executeTask(new AddVegetablePrice(name, price));
                        System.out.println(result);
                        break;
                    }
                    case 2: {
                        System.out.print("Vegetable name: ");
                        String name = scanner.nextLine();
                        System.out.print("New price per kg (KES): ");
                        double price = Double.parseDouble(scanner.nextLine());
                        String result = engine.executeTask(new UpdateVegetablePrice(name, price));
                        System.out.println(result);
                        break;
                    }
                    case 3: {
                        System.out.print("Vegetable name to delete: ");
                        String name = scanner.nextLine();
                        String result = engine.executeTask(new DeleteVegetablePrice(name));
                        System.out.println(result);
                        break;
                    }
                    case 4: {
                        System.out.print("Vegetable name: ");
                        String name = scanner.nextLine();
                        System.out.print("Quantity (kg): ");
                        double qty = Double.parseDouble(scanner.nextLine());
                        String result = engine.executeTask(new CalVegetableCost(name, qty));
                        System.out.println(result);
                        break;
                    }
                    case 5: {
                        System.out.print("Cashier name: ");
                        String cashier = scanner.nextLine();
                        Map<String, Double> order = new LinkedHashMap<>();
                        System.out.println("Enter items (type 'done' when finished):");
                        while (true) {
                            System.out.print("  Vegetable name (or 'done'): ");
                            String veg = scanner.nextLine();
                            if (veg.equalsIgnoreCase("done")) break;
                            System.out.print("  Quantity (kg): ");
                            double qty = Double.parseDouble(scanner.nextLine());
                            order.put(veg, qty);
                        }
                        System.out.print("Amount given by customer (KES): ");
                        double amountGiven = Double.parseDouble(scanner.nextLine());
                        String result = engine.executeTask(new CalculateCost(cashier, order, amountGiven));
                        System.out.println(result);
                        break;
                    }
                    case 6:
                        running = false;
                        System.out.println("Exiting client. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }
            scanner.close();

        } catch (Exception e) {
            System.err.println("Client error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}