package spring.boot.servicephones.command.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.bson.types.ObjectId;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePhoneFixCommand {

    @TargetAggregateIdentifier
    private String id;

    private String idFixPhone;


    private String nameCustoms;
    private String phoneId;
    private String phoneNumCustomer;
    private String gmailCustomer;

    private String detailsError;

    private Boolean isDone;
}
