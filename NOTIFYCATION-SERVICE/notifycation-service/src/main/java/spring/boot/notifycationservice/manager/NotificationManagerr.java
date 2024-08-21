package spring.boot.notifycationservice.manager;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;






//import spring.boot.notifycationservice.ENUM.NotificationType;
//import spring.boot.notifycationservice.ENUM.Templates;
import spring.boot.commonservice.ENUM.NotificationType;
import spring.boot.commonservice.ENUM.Templates;
import spring.boot.notifycationservice.notification.EmailNotification;
import spring.boot.notifycationservice.notification.Notification;

import spring.boot.notifycationservice.strategy.notification.NotificationStrategy;


import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Log4j2
@Component
public class NotificationManagerr {

    // đang có 2 chiến lược email và sms giờ tìm cách lọc và sử dụng
    @Autowired
    private Map<String, NotificationStrategy> notificationStrategyMap;

    @Autowired
    private Map<NotificationType, NotificationStrategy> strategyMap;
    public void send(Notification notification, NotificationType type) {

//        if (notification instanceof EmailNotification) {
//            log.error("notification trong send {}:",notification);
//        }



        Templates template = Templates.fromTemplateNameToGetService(notification.getTemplateName());

        log.error(" đoạn này chưa lỗi 0 {}:");


        // Lọc các chiến lược mà template khớp với giá trị từ notification
        Map<String, NotificationStrategy> filteredStrategies = notificationStrategyMap;



        filteredStrategies.entrySet().stream()
                .filter(entry -> {
                    NotificationStrategy strategy = entry.getValue();
                    return strategy.getTemplate().equals(template);
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));





        NotificationStrategy strategy = filteredStrategies.values().iterator().next();
        log.error("NotificationStrategy {} :", strategy.getClass().getName());
            strategy.sendNotification(notification);
            System.out.println("Thành công: Đã gửi thông báo với chiến lược " + strategy.getClass().getSimpleName());
        if (filteredStrategies.isEmpty()) {
            System.out.println("Không tìm thấy chiến lược phù hợp với template.");
        }






//        switch (type) {
//            case EMAIL:
//                EmailNotification emailNotification = (EmailNotification) notification;
//
//                Templates template = Templates.fromTemplateNameToGetService(emailNotification.getTemplateName());
//                NotificationEmailService service = notificationStrategyMap.get(template.getServiceName());
//                if (service != null) {
//                    service.sendNotificationEmail(emailNotification);
//                } else {
//                    log.warn("No service found for template: {}", template.getTemplateName());
//                }
//
//
//            case SMS:
//                SmsNotification smsNotification = (SmsNotification) notification;
//
//                // Có thể thêm các case khác nếu cần
//            default:
//                throw new IllegalArgumentException("Unknown notification type: " + type);
//        }


    }

    }
