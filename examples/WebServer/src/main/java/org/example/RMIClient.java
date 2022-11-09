package org.example;



import java.math.BigDecimal;
import java.rmi.Naming;

public class RMIClient {
    public static void main(String[] args) {
        String hostName = "localhost";
        int port = 8086;
        String RMI_HOSTNAME = "java.rmi.server.hostname";
        String SERVICE_PATH = "//" + hostName + ":" + port + "/Service";

        try {
            System.setProperty(RMI_HOSTNAME, hostName);
            Service service = (Service) Naming.lookup(SERVICE_PATH);

            while (true) {
                service.timer();
                    Double num = service.pollElem();
                    if (num == null) {
                        break;
                    }
                    num /= 42;
                    System.out.println();
                    System.out.println("Result: " + new BigDecimal(num));
                }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}