package spring.boot.notifycationservice.strategy;

import org.springframework.stereotype.Component;
//import spring.boot.notifycationservice.DTO.MessageDTO;
//import spring.boot.notifycationservice.ENUM.NotificationType;
import spring.boot.commonservice.DTO.MessageDTO;
import spring.boot.commonservice.ENUM.NotificationType;
import spring.boot.notifycationservice.notification.Notification;
import spring.boot.notifycationservice.notification.SmsNotification;

@Component
public class SmsModelStrategy implements NotificationModelStrategy {
    @Override
    public NotificationType getNotificationType() {
        return NotificationType.SMS;
    }

    @Override
    public Notification createNotification(MessageDTO<?> messageDTO) {
        return new SmsNotification(
            messageDTO.getTo(),
            messageDTO.getMessage()
        );
    }
}
