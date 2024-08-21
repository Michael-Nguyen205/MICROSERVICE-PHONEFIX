package spring.boot.servivecustomer.command.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "custom")
public class Customer {
    @Id
    private String id;
    private String name;
    private String phoneNumber;
    private Boolean isBan;



    @Override
    public String toString() {
        return "Phone(id=" + id + ", name=" + name + ", phoneNumber="  + phoneNumber + "isBan :"+isBan+")";
    }
}

