package com.vegetable.rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * The RMI server engine.
 * Registers itself in the RMI registry and executes incoming client tasks.
 */
public class VegetableComputeEngine extends UnicastRemoteObject implements Compute {

    public VegetableComputeEngine() throws RemoteException {
        super();
    }

    /**
     * Executes any Task object sent by the client.
     */
    @Override
    public <T> T executeTask(Task<T> t) throws RemoteException {
        return t.execute();
    }

    /**
     * Main method: starts the RMI registry and binds the engine.
     */
    public static void main(String[] args) {
        try {
            // Start RMI registry on default port 1099
            Registry registry = LocateRegistry.createRegistry(1099);

            VegetableComputeEngine engine = new VegetableComputeEngine();
            registry.rebind("VegetableComputeEngine", engine);

            System.out.println("=== Vegetable Compute Engine is running on port 1099 ===");
            System.out.println("Waiting for client tasks...");
        } catch (RemoteException e) {
            System.err.println("Server error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}