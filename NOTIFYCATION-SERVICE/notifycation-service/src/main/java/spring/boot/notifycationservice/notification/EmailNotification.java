package spring.boot.notifycationservice.notification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailNotification implements Notification {
    private String to;
    private String body;
    private String templateName;
    private Object data;



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
        return templateName;
    }


    }