package spring.boot.commonservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
   public class PhoneFixDTO {

    private String nameCustoms;
    private String phoneId;
    private String phoneNumCustomer;
    private String gmailCustomer;
    private String detailsError;

}
