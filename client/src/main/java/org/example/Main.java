package org.example;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Main {

    private static Socket socket;

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Initializing client...");
        socket = new Socket("127.0.0.1", 4822);

        OutputStream out = socket.getOutputStream();
        DataOutputStream oos = new DataOutputStream(out);
        //wysyłanie informacji o maszynie
        System.out.println(1);
        String s = "Maszyna 1";
        oos.writeUTF(s);

        //obliczanie hash'u
        Thread.sleep(4000);

        //wysyłanie hash'u pliku
        System.out.println(2);
        oos.writeUTF("123afgdfgfdsgsdfg");

        socket.close();
    }
}