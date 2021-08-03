package by.du.chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private static final int PORT = 10_000;

    public static void main(String[] args) {

        final Publisher publisher = new Publisher();

        final ExecutorService service = Executors.newCachedThreadPool();

        try (final ServerSocket serverSocket = new ServerSocket(PORT)) {

            while (true) {
                System.out.println("Server started, waiting connection...");
                final Socket socket = serverSocket.accept();
                System.out.printf("Accepted client with IP: %s\n", socket.getInetAddress());
                service.submit(new ServerListener(socket, publisher)); // new Thread(new ServerListener(socket, publisher)).start();
            }

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
