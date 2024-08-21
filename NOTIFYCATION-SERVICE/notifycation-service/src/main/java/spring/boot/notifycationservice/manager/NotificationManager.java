package spring.boot.notifycationservice.manager;

//import spring.boot.notifycationservice.ENUM.NotificationType;
import spring.boot.commonservice.ENUM.NotificationType;
import spring.boot.notifycationservice.notification.Notification;

public interface NotificationManager {
    void send(Notification notification, NotificationType type);
}
