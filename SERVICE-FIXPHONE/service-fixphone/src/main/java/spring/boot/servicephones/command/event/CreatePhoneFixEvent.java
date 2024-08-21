package spring.boot.servicephones.command.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePhoneFixEvent {



    private String id;
    private String idFixphone;
    private String nameCustoms;
    private String phoneId;
    private String phoneNumber;
    private String gmailCustomer;
    private String detailsError;
    private Boolean isDone;


}
