package spring.boot.servicephones.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.thoughtworks.xstream.XStream;
import org.axonframework.config.Configurer;
import org.axonframework.config.DefaultConfigurer;
import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.eventhandling.TrackingEventProcessorConfiguration;
import org.axonframework.eventhandling.tokenstore.TokenStore;
//import org.axonframework.extensions.mongo.DefaultMongoTemplate;
//import org.axonframework.extensions.mongo.MongoTemplate;
//import org.axonframework.extensions.mongo.eventsourcing.tokenstore.MongoTokenStore;
import org.axonframework.messaging.StreamableMessageSource;
import org.axonframework.serialization.json.JacksonSerializer;
import org.axonframework.serialization.Serializer; // Sửa import để phù hợp
import org.axonframework.serialization.xml.XStreamSerializer;
import org.axonframework.spring.config.AxonConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AxonConfig {
//
//    @Bean
//    public TokenStore tokenStore(MongoClient mongoClient, @Qualifier("customSerializer") Serializer serializer) {
//        MongoTemplate mongoTemplate = DefaultMongoTemplate.builder()
//                .mongoDatabase(mongoClient, "phoneDb")
//                .build();
//        return MongoTokenStore.builder()
//                .mongoTemplate(mongoTemplate)
//                .serializer(serializer) // Không cần ép kiểu ở đây
//                .build();
//    }
//
//    @Bean
//    public MongoClient mongoClient() {
//        return MongoClients.create("mongodb://localhost:27019");
//    }
//
//    @Primary
//    @Bean(name = "customSerializer")
//    public Serializer customSerializer() { // Đặt tên bean là 'customSerializer'
//        return JacksonSerializer.defaultSerializer();
//    }

    public AxonConfig(EventProcessingConfigurer configurer) {
        configurer.registerTrackingEventProcessorConfiguration(
                config -> TrackingEventProcessorConfiguration
                        .forParallelProcessing(1000)
                        .andInitialTrackingToken(StreamableMessageSource::createHeadToken)
        );
   }


//    public Configurer configurer(XStream xStream) {
//        Configurer configurer = DefaultConfigurer.defaultConfiguration();
//        configurer.configureSerializer(c -> new org.axonframework.serialization.xml.XStreamSerializer(xStream));
//        return configurer;
//    }
//
//    @Bean
//    public XStream xStream() {
//        XStream xStream = new XStream();
//        xStream.addPermission(com.thoughtworks.xstream.security.AnyTypePermission.ANY);
//        xStream.allowTypes(new Class[]{spring.boot.commonservice.query.GetDetailPhoneCommonQuery.class});
//        return xStream;
//    }

    @Bean
    public XStream xStream() {
        XStream xStream = new XStream();
        xStream.addPermission(com.thoughtworks.xstream.security.AnyTypePermission.ANY);
        xStream.allowTypes(new Class[]{spring.boot.commonservice.query.GetDetailPhoneCommonQuery.class});
        return xStream;
    }

    @Bean
    public Serializer messageSerializer(XStream xStream) {
        return XStreamSerializer.builder().xStream(xStream).build();
    }

    @Bean
    public Serializer eventSerializer(XStream xStream) {
        return XStreamSerializer.builder().xStream(xStream).build();
    }


}
