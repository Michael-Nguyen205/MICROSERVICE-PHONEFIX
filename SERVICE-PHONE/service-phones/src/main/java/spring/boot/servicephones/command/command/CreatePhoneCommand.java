package spring.boot.servicephones.command.command;

import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

//@Getter
//@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePhoneCommand {
    @TargetAggregateIdentifier
    private  String id;
    private  String name;
    private  Boolean enable;

//    public CreatePhoneCommand(String id, String name, Boolean enable) {
//        this.id = id;
//        this.name = name;
//        this.enable = enable;
//    }

}
