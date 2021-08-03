package by.du.chat;

import java.util.ArrayList;
import java.util.List;

public class Publisher {
    private final List<Subscriber> subscribers = new ArrayList<>();

    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void newMessage(String name, String msg) {
        subscribers.forEach(x -> x.update(name, msg));
    }

}
