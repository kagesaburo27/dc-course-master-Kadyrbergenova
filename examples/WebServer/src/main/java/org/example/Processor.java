package org.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Processor of HTTP request.
 */
public class Processor extends Thread{
    private final Socket socket;
    private final HttpRequest request;

    static List<Integer> list = new ArrayList<>();
    Random rand = new Random();
    public Processor(Socket socket, HttpRequest request) {
        this.socket = socket;
        this.request = request;
    }

    @Override
    public void run() {
        try {
            process();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void process() throws IOException {
        if (request.getRequestLine().contains("/create")) {
            create();
            socket.close();
        } else if (request.getRequestLine().contains("/delete")) {
          delete();
            socket.close();
        } else if (request.getRequestLine().contains("/exec")) {
          exec();
            socket.close();
        } else {
            // Print request that we received.
//            System.out.println("Got request:");
//            System.out.println(request.toString());
//            System.out.flush();


            // To send response back to the client.
            PrintWriter output = new PrintWriter(socket.getOutputStream());

            // We are returning a simple web page now.
            output.println("HTTP/1.1 200 OK");
            output.println("Content-Type: text/html; charset=utf-8");
            output.println();
            output.println("<html>");
            output.println("<head><title>Hello</title></head>");
            output.println("<body><p>Hello, world!</p></body>");
            output.println("</html>");
            output.flush();

        }
    }
    public void create() throws IOException {
        int num = rand.nextInt(2000);
        list.add(num);
        PrintWriter output = new PrintWriter(socket.getOutputStream());
        // We are returning a simple web page now.
        output.println("HTTP/1.1 200 OK");
        output.println("Content-Type: text/html; charset=utf-8");
        output.println();
        output.println("<html>");
        output.println("<head><title>Hello</title></head>");
        output.println("<body><p>Hello, world!</p>");

        output.println(num);

        output.println("</html>");
        output.flush();
    }
    public void delete() throws IOException {
        int i = rand.nextInt(list.size());
        list.remove(i);
        PrintWriter output = new PrintWriter(socket.getOutputStream());
        // We are returning a simple web page now.
        output.println("HTTP/1.1 200 OK");
        output.println("Content-Type: text/html; charset=utf-8");
        output.println();
        output.println("<html>");
        output.println("<head><title>Hello</title></head>");
        output.println("<body><p>Hello, world!</p>");

        output.println(i);

        output.println("</html>");
        output.flush();
    }
    public void exec() throws IOException {
        int N = 500000;
        int x, y, flg;
        PrintWriter output = new PrintWriter(socket.getOutputStream());
        // We are returning a simple web page now.
        output.println("HTTP/1.1 200 OK");
        output.println("Content-Type: text/html; charset=utf-8");
        output.println();
        output.println("<html>");
        output.println("<head><title>Hello</title></head>");
        output.println("<body><p>Hello, world!</p>");
        // Printing display message
        System.out.println(
                "All the Prime numbers within 1 and " + N + " are:");

        // Using for loop for traversing all
        // the numbers from 1 to N
        for (x = 1; x <= N; x++) {
            // Omit 0 and 1 as they are neither prime nor composite
            if (x == 1 || x == 0)
                continue;
            // Using flag variable to check if x is prime or not
            flg = 1;
            for (y = 2; y <= x / 2; ++y) {
                if (x % y == 0) {
                    flg = 0;
                    break;
                }
            }
            // If flag is 1 then x is prime but
            // if flag is 0 then x is not prime
            if (flg == 1)
                output.println(x);
        }



        output.println("</html>");
        output.flush();
    }

}
