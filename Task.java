package com.vegetable.rmi;

import java.io.Serializable;
import java.rmi.RemoteException;

public interface Task<T> extends Serializable {
    T execute(Compute engine) throws RemoteException;
}