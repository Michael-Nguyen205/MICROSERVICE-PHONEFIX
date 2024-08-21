package spring.boot.commonservice.query;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhoneResponeCommonModel {


    private String id;
    private String name;
    private Boolean enable;
}
