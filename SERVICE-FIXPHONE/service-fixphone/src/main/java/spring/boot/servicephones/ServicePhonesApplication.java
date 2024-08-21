package spring.boot.servicephones;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@EnableAsync
@SpringBootApplication
public class ServicePhonesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicePhonesApplication.class, args);
    }



}
