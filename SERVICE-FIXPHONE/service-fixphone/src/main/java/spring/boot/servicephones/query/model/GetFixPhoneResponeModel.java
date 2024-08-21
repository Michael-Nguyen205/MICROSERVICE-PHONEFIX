package spring.boot.servicephones.query.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetFixPhoneResponeModel {

    private String id;
    private String nameCustoms;
    private String phoneId;
    private String phoneNumber;
    private String gmailCustomer;
    private String detailsError;
    private Boolean isDone;



}
