package by.du.chat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Client {

    private static final int PORT = 10000;
    private static final String HOST = "localhost";

    public static void main(String[] args) {
        try (final Socket socket = new Socket(HOST, PORT)) {

            try (final InputStream in = socket.getInputStream();
                 final OutputStream out = socket.getOutputStream()) {

                String msg = "Hi";
                out.write(msg.getBytes(StandardCharsets.UTF_8));
                out.flush();

                final byte[] buffer = new byte[4096];
                final int data = in.read(buffer);

                System.out.printf("Server: %s", new String(buffer, 0, data));
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
