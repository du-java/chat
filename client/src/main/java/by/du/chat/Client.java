package by.du.chat;

import java.io.*;
import java.net.Socket;

public class Client {

    private static final int PORT = 10000;
    private static final String HOST = "localhost";

    public static void main(String[] args) {
        try (final Socket socket = new Socket(HOST, PORT)) {

            try (final DataInputStream in = new DataInputStream(socket.getInputStream());
                 final DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

                String req = "Hi";
                out.writeUTF(req);
                out.flush();

                final String resp = in.readUTF();

                System.out.printf("Server: %s\n", resp);
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
