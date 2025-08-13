package com.gl.app.model;

import java.io.*;
import java.net.*;

// Handles each client in a separate thread
class ClientHandler implements Runnable {
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        ) {
            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Received from client: " + message);
                out.println("Echo: " + message); // Echo back
            }
        } catch (IOException e) {
            System.out.println("Client disconnected.");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

// Server class
class Server implements Runnable {
    private int port;

    public Server(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected");
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// Client class
class Client implements Runnable {
    private String host;
    private int port;
    private String message;

    public Client(String host, int port, String message) {
        this.host = host;
        this.port = port;
        this.message = message;
    }

    @Override
    public void run() {
        try (
                Socket socket = new Socket(host, port);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        ) {
            out.println(message);
            String response = in.readLine();
            System.out.println("Client received: " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// Main class
public class MultiThreadedEcho {
    public static void main(String[] args) {
        int port = 12345;

        // Start the server in its own thread
        Thread serverThread = new Thread(new Server(port));
        serverThread.start();

        try {
            // Give the server time to start
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Start two clients
        Thread client1 = new Thread(new Client("localhost", port, "Hello from Client 1"));
        Thread client2 = new Thread(new Client("localhost", port, "Hello from Client 2"));

        client1.start();
        client2.start();
    }
}
