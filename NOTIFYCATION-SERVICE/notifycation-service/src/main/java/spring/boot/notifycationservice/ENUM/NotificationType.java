//package spring.boot.notifycationservice.ENUM;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import java.util.Arrays;
//
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//public enum NotificationType {
//    EMAIL("email", "EmailNotificationStrategy"),
//    SMS("sms",  "SmsNotificationStrategy");
//
//    private  String type;
//    private  String strategyName;
//
//    public static NotificationType fromNotificationType(String type) {
//        if (type == null) {
//            throw new IllegalArgumentException("Notification type cannot be null");
//        }
//        return Arrays.stream(values())
//                .filter(status -> status.getType().equals(type))
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException("No enum constant with code " + type));
//    }
//
//
//
//
//    public static NotificationType fromString(String strategyName) {
//        for (NotificationType notificationType : NotificationType.values()) {
//            if (notificationType.getStrategyName().equalsIgnoreCase(strategyName)) {
//                return notificationType;
//            }
//        }
//        throw new IllegalArgumentException("Unknown strategy name: " + strategyName);
//    }
//}
