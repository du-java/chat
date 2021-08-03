package by.du.chat;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private static final int PORT = 10_000;
    private static final String HOST = "localhost";
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {

        if (args.length == 0) return;

        final String name = args[0];
        System.out.printf("Client: %s\n", name);
        try (final Socket socket = new Socket(HOST, PORT)) {

            new Thread(new ClientListener(socket)).start();

            try (final DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {
                send(name, out);

                while (true) {
                    System.out.println("Enter msg: ");
                    send(SCANNER.nextLine(), out);
                }
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private static void send(String msg, DataOutputStream out) throws IOException {
        out.writeUTF(msg);
        out.flush();
    }
}
