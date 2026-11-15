package dip;

import java.util.Objects;

public class NotificationService {
    private final MessageSender messageSender;

    public NotificationService(MessageSender messageSender) {
        this.messageSender = Objects.requireNonNull(messageSender);
    }

    public void send(String message) {
        messageSender.send(message);
    }
}
