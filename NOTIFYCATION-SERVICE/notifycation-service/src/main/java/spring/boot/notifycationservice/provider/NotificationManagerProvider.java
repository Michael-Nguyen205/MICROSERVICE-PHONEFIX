//package spring.boot.notifycationservice.provider;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import spring.boot.notifycationservice.ENUM.NotificationType;
//import spring.boot.notifycationservice.manager.NotificationManager;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Component
//public class NotificationManagerProvider {
//    private final Map<String, NotificationManager> notificationManagerMap;
//
//
//    @Autowired
//    public NotificationManagerProvider(List<NotificationManager> notificationManagers) {
//        this.notificationManagerMap = new HashMap<>();
//        for (NotificationManager manager : notificationManagers) {
//            if (manager instanceof EmailServiceManager) {
//                notificationManagerMap.put(NotificationType.EMAIL.toString(), manager);
//            }
////            else if (manager instanceof SmsServiceManager) {
////                notificationManagerMap.put(NotificationType.SMS, manager);
////            }
//        }
//    }
//
//
//    public NotificationManager getNotificationManager(NotificationType type) {
//        return notificationManagerMap.get(type.toString());
//    }
//}
