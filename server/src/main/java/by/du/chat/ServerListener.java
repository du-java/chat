package by.du.chat;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerListener implements Runnable {
    public ServerListener(Socket socket, Publisher publisher) {
        this.socket = socket;
        this.publisher = publisher;
    }

    private final Socket socket;
    private final Publisher publisher;

    @Override
    public void run() {
        try (final DataInputStream in = new DataInputStream(socket.getInputStream())) {

            publisher.subscribe(new Subscriber(socket));
            String name = in.readUTF();

            while (true) {
                final String msg = in.readUTF();
                System.out.printf("%s: %s\n", name, msg);
                publisher.newMessage(name, msg);
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
