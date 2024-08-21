package spring.boot.servicephones.command.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.bson.types.ObjectId;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStatusPhoneFixCommand {


    @TargetAggregateIdentifier
    private ObjectId id;
    private ObjectId PhoneFixId;
    private Boolean isdone;
}
