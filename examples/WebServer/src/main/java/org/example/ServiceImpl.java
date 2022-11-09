package org.example;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Implementation of the remote service.
 */
public class ServiceImpl extends UnicastRemoteObject implements Service {
    private final BlockingQueue<Double> queue;
    boolean start = true;
    static LocalDateTime starttime, endtime;
    public ServiceImpl() throws RemoteException {
        super();
        this.queue = new LinkedBlockingQueue<>();
    }


    public Double pollElem() throws RemoteException {
        if (this.queue.isEmpty()) {
            end();
        }
        return this.queue.poll();

    }

    public void addElem(Double num) throws RemoteException {
        this.queue.add(num);
        System.out.println("Added: " + num);
    }
    public void timer() throws RemoteException {
        if (start) {
            starttime = LocalDateTime.now();
        }
        start = false;
    }
    public void end() throws RemoteException {
        endtime = LocalDateTime.now();
        System.out.println("Time started: " + starttime);
        System.out.println("Time finished: " + endtime);
        Duration duration = Duration.between(starttime, endtime);
        System.out.println("Time taken: " + (duration.toMillis()) + " milliseconds");
    }
}