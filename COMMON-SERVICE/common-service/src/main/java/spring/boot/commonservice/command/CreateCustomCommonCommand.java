package spring.boot.commonservice.command;


import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomCommonCommand {

    @TargetAggregateIdentifier
    private String id;
    private String name;
    private String phoneNumber;
    private Boolean isBan;


}
