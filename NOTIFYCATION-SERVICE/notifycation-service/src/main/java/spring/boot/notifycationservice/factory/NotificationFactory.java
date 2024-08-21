package spring.boot.notifycationservice.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.boot.commonservice.DTO.MessageDTO;
//import spring.boot.notifycationservice.ENUM.NotificationType;
import spring.boot.commonservice.ENUM.NotificationType;
import spring.boot.notifycationservice.notification.Notification;
import spring.boot.notifycationservice.strategy.NotificationModelStrategy;
import java.util.Map;


@Component
public class NotificationFactory {
    @Autowired
    private Map<NotificationType, NotificationModelStrategy> strategyMap;

    public Notification createNotification(MessageDTO<?> messageDTO) {

        NotificationType type
                = NotificationType.fromNotificationType(messageDTO.getType());

        return strategyMap.get(type).createNotification(messageDTO);
    }





//    public static Notification createNotification(MessageDTO<?> messageDTO) {
//
//        NotificationType type = NotificationType.fromString(messageDTO.getType());
//
//
//        switch (type) {
//            case EMAIL:
//                return new EmailNotification(
//                        messageDTO.getTo(),
//                        messageDTO.getMessage(),
//                        messageDTO.getTemplate(), // Tên template từ messageDTO
//                        messageDTO.getData()
//                );
//            case SMS:
//                return new SmsNotification(
//                        messageDTO.getTo(),
//                        messageDTO.getMessage()
//                );
//            // Có thể thêm các case khác nếu cần
//            default:
//                throw new IllegalArgumentException("Unknown notification type: " + type);
//        }
//
//    }
}
