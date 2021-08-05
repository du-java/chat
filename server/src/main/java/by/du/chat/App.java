package by.du.chat;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("by.du.chat");
        context.refresh();
        final Server server = context.getBean(Server.class);
//        final Server server1 = context.getBean(Server.class);
        server.start();
    }
}
