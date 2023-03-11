package org.example;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ConnectionThread implements Runnable {

    private final Socket conn;

    public ConnectionThread(Socket socket) {
        this.conn = socket;
        System.out.println(socket.getInetAddress().getHostAddress() + " connected");
        run();
    }


    @Override
    public void run() {
        try {
            while (!conn.isClosed()) {
                DataInputStream in = new DataInputStream(conn.getInputStream());
                System.out.println("reading");
                String s = in.readUTF();
                System.out.println(s);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() throws IOException {
        conn.close();
    }
}
