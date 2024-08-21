package spring.boot.servivecustomer.command.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerEvent {
    private  String id;
    private String name;
    private String phoneNumber;
    private Boolean isBan;


}

