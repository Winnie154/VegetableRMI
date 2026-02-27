package com.vegetable.rmi;

import java.io.Serializable;
import java.rmi.RemoteException;

/**
 * Task interface that all client tasks must implement.
 * Each task is serializable so it can be sent over RMI.
 */
public interface Task<T> extends Serializable {
    T execute() throws RemoteException;
}