package by.du.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

@Component
//@Scope(scopeName = "prototype")
@RequiredArgsConstructor
public class Server {

    @Value("${server.port}")
    private int port;

//    @Autowired
//    public Server(ExecutorService service, Publisher publisher) {
//        this.service = service;
//        this.publisher = publisher;
//    }

    private final ExecutorService service;
    private final Publisher publisher;

//    @Autowired
//    @Qualifier("pub1")
//    private Publisher publisher;


//    @Autowired
//    private ExecutorService service;
//
//    private Publisher publisher;
//
//    @Autowired
//    public void setPublisher(Publisher publisher) {
//        this.publisher = publisher;
//    }

    public void start() {

        try (final ServerSocket serverSocket = new ServerSocket(port)) {

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

    @PostConstruct
    private void postConstruct() {
        System.out.println("@PostConstruct");
    }

    public void init() {
        System.out.println("init");
    }

    @PreDestroy
    private void preDestroy() {
        System.out.println("@PreDestroy");
    }
}
