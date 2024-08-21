package spring.boot.notifycationservice.configuration;


//import io.github.resilience4j.timelimiter.TimeLimiterConfig;
//import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
//import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
//import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
//import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

@Configuration

public class CircuitBreakerConfig {

    @Bean
    public WebClient.Builder getWebClientBuilder(){
        return WebClient.builder();
    }


//    @Bean
//    public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer() {
//        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
//                .timeLimiterConfig(TimeLimiterConfig.custom()
//                        .timeoutDuration(Duration.ofSeconds(3))
//                        .build())
//                .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults()
//                        .toBuilder()
//                        .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
//                        .slidingWindowSize(10)
//                        .minimumNumberOfCalls(5)
//                        .failureRateThreshold(50)
//                        .build())
//                .build()
//        );
//    }

//    @Bean
//    public Customizer<ReactiveResilience4JCircuitBreakerFactory> slowCustomizer() {
//        return factory -> {
//            factory.configure(builder -> builder
//                    .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(2)).build())
//                    .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults()), "slow", "slowflux");
//            factory.addCircuitBreakerCustomizer(circuitBreaker -> circuitBreaker.getEventPublisher()
//                    .onError(normalFluxErrorConsumer).onSuccess(normalFluxSuccessConsumer), "normalflux");
//        };
//    }






}
