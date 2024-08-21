package spring.boot.notifycationservice.configuration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
//import spring.boot.notifycationservice.ENUM.NotificationType;
import spring.boot.commonservice.ENUM.NotificationType;
import spring.boot.notifycationservice.strategy.NotificationModelStrategy;
import spring.boot.notifycationservice.strategy.notification.NotificationStrategy;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class NotificationConfig {

    @Autowired
    private List<NotificationModelStrategy> strategiesModel;


    @Autowired
    private List<NotificationStrategy> strategies;


    @Autowired
    private ApplicationContext applicationContext;


    @Bean
    public Map<NotificationType, NotificationModelStrategy> strategiesModelMap() {
        return strategiesModel.stream()
            .collect(Collectors.toMap(
                    NotificationModelStrategy::getNotificationType,
                    strategy -> strategy
            ));
    }



    @Bean
    public Map<NotificationType, NotificationStrategy> strategyMap() {
        return strategies.stream()
                .collect(Collectors.toMap(
                        NotificationStrategy::getNotificationType,
                        strategy -> strategy
                ));
    }







    @Bean
    public List<NotificationStrategy> animalServices() {
        return applicationContext.getBeansOfType(NotificationStrategy.class).values().stream().collect(Collectors.toList());
    }



}
