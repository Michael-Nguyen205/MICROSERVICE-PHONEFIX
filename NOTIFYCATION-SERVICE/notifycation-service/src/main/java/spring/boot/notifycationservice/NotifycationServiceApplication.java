package spring.boot.notifycationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.util.backoff.FixedBackOff;

import java.time.Duration;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

@SpringBootApplication
@EnableAsync

public class NotifycationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotifycationServiceApplication.class, args);
    }

//
//    @Bean
//    public DefaultErrorHandler errorHandler(KafkaTemplate<String, Object> kafkaTemplate) {
//        return new DefaultErrorHandler(
//                new DeadLetterPublishingRecoverer(kafkaTemplate),
//                new FixedBackOff(Duration.ofSeconds(1).toMillis(), 2)
//        );
//    }





//
//    @Bean
//    public Jackson2JsonMessageConverter converter() {
//        return new Jackson2JsonMessageConverter();
//    }
}
