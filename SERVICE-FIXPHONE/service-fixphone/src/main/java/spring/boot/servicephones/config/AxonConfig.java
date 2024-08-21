package spring.boot.servicephones.config;

import com.thoughtworks.xstream.XStream;
import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.eventhandling.TrackingEventProcessorConfiguration;
import org.axonframework.messaging.StreamableMessageSource;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.xml.XStreamSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class AxonConfig {


    public AxonConfig(EventProcessingConfigurer configurer) {
        configurer.registerTrackingEventProcessorConfiguration(
                config -> TrackingEventProcessorConfiguration
                        .forSingleThreadedProcessing()
                        .andInitialTrackingToken(StreamableMessageSource::createHeadToken)
        );
    }


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
