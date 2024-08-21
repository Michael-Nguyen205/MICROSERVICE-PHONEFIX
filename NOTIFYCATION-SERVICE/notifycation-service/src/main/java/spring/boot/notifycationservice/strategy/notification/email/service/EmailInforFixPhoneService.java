package spring.boot.notifycationservice.strategy.notification.email.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring5.SpringTemplateEngine;
import spring.boot.commonservice.DTO.PhoneFixDTO;
//import spring.boot.notifycationservice.ENUM.NotificationType;
//import spring.boot.notifycationservice.ENUM.Templates;
import spring.boot.commonservice.ENUM.Templates;
import spring.boot.notifycationservice.notification.EmailNotification;
import spring.boot.notifycationservice.notification.Notification;

import org.thymeleaf.context.Context; // Đảm bảo bạn đang sử dụng Context từ Thymeleaf
import spring.boot.notifycationservice.strategy.notification.email.EmailNotificationStrategy;


import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Service
public class EmailInforFixPhoneService extends EmailNotificationStrategy {


        @Autowired
        private  JavaMailSender javaMailSender;

        @Autowired
        private  SpringTemplateEngine templateEngine;

        @Override
        public Templates getTemplate() {
                return Templates.INFO_FIX_PHONE_TEMPLATE;
        }


        @Override
        public void sendNotification(Notification notification) {



                EmailNotification emailNotification = (EmailNotification) notification;
                try {
                        MimeMessage message = javaMailSender.createMimeMessage();
                        MimeMessageHelper helper = new MimeMessageHelper(message, StandardCharsets.UTF_8.name());

                        // Load template email với nội dung
                        Context context = new Context(); // Sử dụng Context từ Thymeleaf
                        context.setVariable("message", emailNotification.getBody());

                        // Thiết lập các biến từ dữ liệu emailNotification.getData()
                        if (emailNotification.getData() instanceof PhoneFixDTO) {
                                PhoneFixDTO dto = (PhoneFixDTO) emailNotification.getData();
                                context.setVariable("phoneId", dto.getPhoneId());
                                context.setVariable("name", dto.getNameCustoms());
                                context.setVariable("phoneNumber", dto.getPhoneNumCustomer());
                                context.setVariable("details", dto.getDetailsError());
                        }

                        String html = templateEngine.process(emailNotification.getTemplateName(), context);

                        helper.setTo(emailNotification.getTo());
                        helper.setText(html, true);
                        helper.setSubject("Thông tin sửa điện thoại");
                        helper.setFrom("no-reply@example.com");

                        javaMailSender.send(message);
                } catch (Exception e) {
                        // Xử lý ngoại lệ
                        e.printStackTrace();
                }
        }
}
