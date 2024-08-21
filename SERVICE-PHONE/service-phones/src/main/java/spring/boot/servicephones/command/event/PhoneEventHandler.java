package spring.boot.servicephones.command.event;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;
import spring.boot.servicephones.command.model.Phone;
import spring.boot.servicephones.command.model.PhoneRepository;
import spring.boot.commonservice.command.CreateCustomCommonCommand;

@Log4j2
@RequiredArgsConstructor
@Component
public class PhoneEventHandler {
    private final PhoneRepository phoneRepository;


    @EventHandler
     public void on(CreatePhoneEvent event){
     Phone phone = new Phone();
        phone.setId(event.getId());
        phone.setName(event.getName());
        phone.setEnable(event.getEnable());
        phoneRepository.save(phone);
        log.error(phone.toString());
    }

}
