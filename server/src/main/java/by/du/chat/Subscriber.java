package by.du.chat;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Subscriber {

    public Subscriber(Socket socket) {
        this.socket = socket;
    }

    private final Socket socket;

    public void update(String name, String msg) {
        try {
            final DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(String.format("%s: %s", name, msg));
            out.flush();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
