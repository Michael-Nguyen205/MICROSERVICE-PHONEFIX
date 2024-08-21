package spring.boot.servivecustomer.command.event;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import spring.boot.servivecustomer.command.model.Customer;
import spring.boot.servivecustomer.command.model.CustomerRepository;

@Log4j2
@RequiredArgsConstructor
@Component
public class CustomerEventHandler {

    @Autowired
    private CustomerRepository  customerRepository;

    @EventHandler
     public void on(CreateCustomerEvent event){

        Customer customer = new Customer();
        customer.setName(event.getName());
        customer.setPhoneNumber(event.getPhoneNumber());
        customer.setIsBan(false);
        log.error(customer.toString());


        try {
            customerRepository.save(customer)
                    .doOnSuccess(savedCustomer -> log.info("Customer saved successfully: {}", savedCustomer))
                    .doOnError(e -> log.error("Error saving customer: {}", customer, e))
                    .block();
        } catch (Exception e) {
            log.error("Error saving customer: {}", customer, e);
        }
    }

}
