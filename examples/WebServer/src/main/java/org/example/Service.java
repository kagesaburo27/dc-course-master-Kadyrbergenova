package org.example;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface for a service which will be accessible remotely
 */
public interface Service extends Remote {
    Double pollElem() throws RemoteException;
    void addElem(Double num) throws RemoteException;
    void timer() throws RemoteException;

    void end() throws RemoteException;
}