package spring.boot.notifycationservice.strategy.notification;

import org.springframework.stereotype.Component;
//import spring.boot.notifycationservice.ENUM.NotificationType;
//import spring.boot.notifycationservice.ENUM.Templates;
import spring.boot.commonservice.ENUM.NotificationType;
import spring.boot.commonservice.ENUM.Templates;
import spring.boot.notifycationservice.notification.Notification;



@Component
public interface NotificationStrategy {

    Templates getTemplate();


    NotificationType getNotificationType();




    void sendNotification(Notification notification);




}
