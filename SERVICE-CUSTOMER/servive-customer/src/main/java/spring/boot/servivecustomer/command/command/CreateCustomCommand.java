package spring.boot.servivecustomer.command.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.springframework.data.annotation.Id;

//@Getter
//@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomCommand {
    @TargetAggregateIdentifier
    private String idComand;
//    private String id;
    private String name;
    private String phoneNumber;
    private Boolean isBan;



//    public CreatePhoneCommand(String id, String name, Boolean enable) {
//        this.id = id;
//        this.name = name;
//        this.enable = enable;
//    }

}
