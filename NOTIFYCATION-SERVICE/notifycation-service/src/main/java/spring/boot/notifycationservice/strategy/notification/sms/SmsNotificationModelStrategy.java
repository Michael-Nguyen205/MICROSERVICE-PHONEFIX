package spring.boot.notifycationservice.strategy.notification.sms;

//import spring.boot.notifycationservice.DTO.MessageDTO;
//import spring.boot.notifycationservice.ENUM.NotificationType;
import org.springframework.stereotype.Component;
import spring.boot.commonservice.ENUM.NotificationType;
import spring.boot.notifycationservice.notification.Notification;
import spring.boot.notifycationservice.strategy.NotificationModelStrategy;
import spring.boot.notifycationservice.strategy.notification.NotificationStrategy;



@Component
public abstract class SmsNotificationModelStrategy implements NotificationStrategy {

    @Override
    public NotificationType getNotificationType() {
        return NotificationType.SMS;
    }

    @Override
    public void sendNotification(Notification notification) {

    }

}
