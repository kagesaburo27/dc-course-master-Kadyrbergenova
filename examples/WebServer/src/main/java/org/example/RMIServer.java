package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {
    public static void main(String[] args) {
        String hostName = "localhost";
        int port = 8086;
        String RMI_HOSTNAME = "java.rmi.server.hostname";
        try {
            System.setProperty(RMI_HOSTNAME, hostName);
            // Create service for RMI
            Service service = new ServiceImpl();
            // Init service
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\kages\\OneDrive\\Рабочий стол\\hw\\dc-course-master\\examples\\WebServer\\src\\main\\java\\org\\example\\numbers.txt"));
            String line = br.readLine();

                String[] tokens = line.split("\\s+");
                for (String token : tokens) {
                    double token1 = Double.parseDouble(token);
                    service.addElem(token1);
                }


            String serviceName = "Service";

            System.out.println("Initializing " + serviceName);

            Registry registry = LocateRegistry.createRegistry(port);
            // Make service available for RMI
            registry.rebind(serviceName, service);

            System.out.println("Start " + serviceName);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}