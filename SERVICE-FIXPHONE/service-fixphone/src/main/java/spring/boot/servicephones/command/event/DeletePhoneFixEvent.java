package spring.boot.servicephones.command.event;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeletePhoneFixEvent {
    private ObjectId id;

}
