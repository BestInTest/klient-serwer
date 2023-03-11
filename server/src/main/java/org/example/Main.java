package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static ServerSocket serverSocket;

    private static List<ConnectionThread> activeConnections = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        System.out.println("Initializing server...");
        serverSocket = new ServerSocket(4822);
        registerExitListener();
        System.out.println("Waiting for connections");

        while (true) {
            activeConnections.add(new ConnectionThread(serverSocket.accept()));
        }
    }

    private static void registerExitListener() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            for (ConnectionThread connection : activeConnections) {
                try {
                    connection.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }));
    }
}