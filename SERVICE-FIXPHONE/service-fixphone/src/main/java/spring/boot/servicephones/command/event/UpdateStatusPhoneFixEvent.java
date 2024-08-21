package spring.boot.servicephones.command.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStatusPhoneFixEvent {

    private ObjectId PhoneFixId;
    private  Boolean  isDone;
}
