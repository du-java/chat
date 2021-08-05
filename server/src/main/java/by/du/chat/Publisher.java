package by.du.chat;

public interface Publisher {
    void subscribe(Subscriber subscriber);

    void newMessage(String name, String msg);
}
