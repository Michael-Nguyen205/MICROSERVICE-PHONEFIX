package spring.boot.notifycationservice.notification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SmsNotification implements Notification {
    private String to;
    private String body;



    @Override
    public String getTo() {
        return to;
    }

    @Override
    public String getBody() {
        return body;
    }

    @Override
    public String getTemplateName() {
        return null; // SMS không cần template
    }
}
