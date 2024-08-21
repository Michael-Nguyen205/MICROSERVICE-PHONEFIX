package spring.boot.notifycationservice.strategy;

import lombok.Getter;
import org.springframework.stereotype.Component;
//import spring.boot.notifycationservice.DTO.MessageDTO;
//import spring.boot.notifycationservice.ENUM.NotificationType;
import spring.boot.commonservice.DTO.MessageDTO;
import spring.boot.commonservice.ENUM.NotificationType;
import spring.boot.notifycationservice.notification.EmailNotification;
import spring.boot.notifycationservice.notification.Notification;


@Getter
@Component
public class EmailModelStrategy implements NotificationModelStrategy {

    @Override
    public NotificationType getNotificationType() {
        return NotificationType.EMAIL;
    }

    @Override
    public Notification createNotification(MessageDTO<?> messageDTO) {
        return new EmailNotification(
            messageDTO.getTo(),
            messageDTO.getMessage(),
            messageDTO.getTemplate(),
            messageDTO.getData()
        );
    }
}
