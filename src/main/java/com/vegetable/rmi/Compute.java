package com.vegetable.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Remote interface that the server exposes via RMI registry.
 * Clients call executeTask() to run any Task remotely.
 */
public interface Compute extends Remote {
    <T> T executeTask(Task<T> t) throws RemoteException;
}