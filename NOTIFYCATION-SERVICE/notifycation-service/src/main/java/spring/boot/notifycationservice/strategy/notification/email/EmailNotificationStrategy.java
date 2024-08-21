package spring.boot.notifycationservice.strategy.notification.email;
import org.springframework.stereotype.Component;
//import spring.boot.notifycationservice.ENUM.NotificationType;
//import spring.boot.notifycationservice.ENUM.Templates;
import spring.boot.commonservice.ENUM.NotificationType;
import spring.boot.commonservice.ENUM.Templates;
import spring.boot.notifycationservice.notification.Notification;
import spring.boot.notifycationservice.strategy.notification.NotificationStrategy;



@Component
public abstract class EmailNotificationStrategy implements NotificationStrategy {


    @Override
    public NotificationType getNotificationType() {
        return NotificationType.EMAIL;
    }


    @Override
    public abstract Templates getTemplate();




    @Override
    public void sendNotification(Notification notification) {

    }


//    protected abstract void specificEmailService(MessageDTO<?> message);
}
