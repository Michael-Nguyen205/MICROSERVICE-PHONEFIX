package spring.boot.commonservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;



@Builder

@Data
@NoArgsConstructor
@AllArgsConstructor

// tuyêt vời lỗi
public class MessageDTO<T> implements Serializable  {
    private String message;
    private String type;  // gmai , email
    private String to;
    private String template; //  infofixphone , sale
    private T data;


    @Override
    public String toString() {
        return "Message [data=" + data + ", message=" + message + "]";
    }
    }

