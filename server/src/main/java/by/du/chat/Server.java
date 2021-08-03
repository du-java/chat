package by.du.chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private static final int PORT = 10000;

    public static void main(String[] args) {

        final ExecutorService service = Executors.newCachedThreadPool();

        try (final ServerSocket serverSocket = new ServerSocket(PORT)) {

            while (true) {
                System.out.println("Server started, waiting connection...");
                final Socket socket = serverSocket.accept();
                service.submit(new ServerThread(socket));
            }

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}

class ServerThread implements Runnable {

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    private final Socket socket;

    @Override
    public void run() {
        System.out.printf("Accepted client with IP: %s\n", socket.getInetAddress());
        try (final DataInputStream in = new DataInputStream(socket.getInputStream());
             final DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

            final String req = in.readUTF();

            System.out.printf("Client: %s\n", req);

            out.writeUTF(req);
            out.flush();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
