package by.du.chat;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientListener implements Runnable {

    private final Socket socket;

    public ClientListener(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            final DataInputStream is = new DataInputStream(socket.getInputStream());

            while (true) {
                System.out.println(is.readUTF());
            }

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

    }
}
