package spring.boot.servicephones.command.event;

import lombok.*;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePhoneEvent {
    private  String id;
    private  String name;
    private  Boolean enable;


}

