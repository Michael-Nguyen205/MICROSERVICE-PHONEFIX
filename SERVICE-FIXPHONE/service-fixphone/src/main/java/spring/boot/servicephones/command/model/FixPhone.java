package spring.boot.servicephones.command.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "fixPhone")
public class FixPhone {

    @Id
    private String id;
    private String nameCustoms;
    private String phoneId;
    private String phoneNumber;
    private String gmailCustomer;
    private String detailsError;
    private Boolean isDone;



}
