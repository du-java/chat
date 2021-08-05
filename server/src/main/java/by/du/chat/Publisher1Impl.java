package by.du.chat;

import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Primary
@Component("pub1")
public class Publisher1Impl implements Publisher{
    private final List<Subscriber> subscribers = new ArrayList<>();

    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void newMessage(String name, String msg) {
        subscribers.forEach(x -> x.update(name, msg));
    }

}
