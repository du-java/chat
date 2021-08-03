package by.du.chat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Server {

    private static final int PORT = 10000;

    public static void main(String[] args) {
        try (final ServerSocket serverSocket = new ServerSocket(PORT)) {

            final Socket socket = serverSocket.accept();

            try (final InputStream in = socket.getInputStream();
                 final OutputStream out = socket.getOutputStream()) {

                final byte[] buffer = new byte[4096];
                final int data = in.read(buffer);

                final String msg = new String(buffer, 0, data);
                System.out.printf("Client: %s", msg);

                out.write(msg.getBytes(StandardCharsets.UTF_8));
                out.flush();
            }

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
