package spring.boot.notifycationservice.notification;// package spring.boot.notifycationservice.notifications


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
public interface Notification {
    String getTo();
    String getBody();
    String getTemplateName();



}
