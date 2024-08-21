package spring.boot.servivecustomer.query.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponeModel {

    private String name;
    private String phoneNumber;
    private Boolean isBan;


}
