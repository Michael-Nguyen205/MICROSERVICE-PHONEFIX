package spring.boot.notifycationservice.strategy;

//import spring.boot.notifycationservice.DTO.MessageDTO;
import spring.boot.commonservice.DTO.MessageDTO;
//import spring.boot.notifycationservice.ENUM.NotificationType;
import spring.boot.commonservice.ENUM.NotificationType;
import spring.boot.notifycationservice.notification.Notification;

public interface NotificationModelStrategy {
    NotificationType getNotificationType();

    Notification createNotification(MessageDTO<?> messageDTO);
}
