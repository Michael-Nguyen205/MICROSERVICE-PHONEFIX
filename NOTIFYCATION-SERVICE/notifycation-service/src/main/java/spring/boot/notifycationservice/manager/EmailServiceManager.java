//package spring.boot.notifycationservice.manager;// package spring.boot.notifycationservice.service
//
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import spring.boot.notifycationservice.ENUM.NotificationType;
//
//import spring.boot.notifycationservice.notification.Notification;
//import spring.boot.notifycationservice.dontcare.email.NotificationEmailService;
//
//import java.util.Map;
//
//@Component
//@NoArgsConstructor
//@AllArgsConstructor
//@Log4j2
//public class EmailServiceManager implements  NotificationManager {
//
//    @Autowired
//    private Map<String, NotificationEmailService> notificationEmailServiceMap;
//
//
////    @Autowired
////    private Map<String, NotificationSmsService> notificationSmsServiceMap;
//
//    @Override
//    public void send(Notification notification, NotificationType type) {
//
//
//
//
//
//
//
//
//
//
//
//
////        EmailNotification emailNotification = (EmailNotification) notification;
////        Templates template = Templates.fromTemplateNameToGetService(emailNotification.getTemplateName());
////        NotificationEmailService service = notificationEmailServiceMap.get(template.getServiceName());
////        if (service != null) {
////            service.sendNotificationEmail(emailNotification);
////        } else {
////            log.warn("No service found for template: {}", template.getTemplateName());
////        }
//
//    }
//}
//
//
//
//
