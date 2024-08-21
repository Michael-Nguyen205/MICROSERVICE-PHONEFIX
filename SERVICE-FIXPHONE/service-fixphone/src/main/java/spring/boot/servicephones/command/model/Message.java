package spring.boot.servicephones.command.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "message")
public class Message<T> {

    @Id
    private String id;
    private String message;
    private Boolean status;
    private T data;

    public void setData(Object data) {
        this.data = (T) data;
    }

}
