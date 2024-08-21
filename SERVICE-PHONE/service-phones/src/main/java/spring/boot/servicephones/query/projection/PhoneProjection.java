package spring.boot.servicephones.query.projection;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;
import spring.boot.commonservice.query.GetDetailPhoneCommonQuery;
import spring.boot.commonservice.query.PhoneResponeCommonModel;
import spring.boot.servicephones.command.model.Phone;
import spring.boot.servicephones.command.model.PhoneRepository;
import org.springframework.beans.BeanUtils;



@Log4j2
@RequiredArgsConstructor
@Component
public class PhoneProjection {
    private final PhoneRepository phoneRepository;


        @QueryHandler
        public PhoneResponeCommonModel handle(  GetDetailPhoneCommonQuery getDetailPhoneCommonQuery) {

            log.error("ddax va0 day");

            PhoneResponeCommonModel model = new PhoneResponeCommonModel();
            Phone phone = phoneRepository.findById(getDetailPhoneCommonQuery.getId()).orElseThrow(
                    () ->new RuntimeException("phone not found")
            );

            BeanUtils.copyProperties(phone, model);


            log.error(model);

            return model;
        }


}
