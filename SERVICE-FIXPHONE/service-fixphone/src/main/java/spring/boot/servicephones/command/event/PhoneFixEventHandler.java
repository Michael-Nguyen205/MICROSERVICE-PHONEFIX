package spring.boot.servicephones.command.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.boot.servicephones.command.model.FixPhone;
import spring.boot.servicephones.command.model.PhoneFixRepository;
import spring.boot.servicephones.service.SendEmailService;



@Log4j2
@RequiredArgsConstructor
@Component
public class PhoneFixEventHandler {


    @Autowired
    private SendEmailService sendEmailService;

    private final PhoneFixRepository phoneRepository;

    @EventHandler
    public void on(CreatePhoneFixEvent event) {
        log.error("da vao event create");

        // Tạo đối tượng PhoneFix từ sự kiện
        FixPhone fixPhone = new FixPhone();
        fixPhone.setId( event.getIdFixphone().toString());


        fixPhone.setGmailCustomer(event.getGmailCustomer());
        fixPhone.setNameCustoms(event.getNameCustoms());  // Đặt tên khách hàng từ sự kiện
        fixPhone.setPhoneId(event.getPhoneId());  // Đặt số điện thoại khách hàng từ sự kiện
        fixPhone.setPhoneNumber(event.getPhoneNumber());  // Đặt tên điện thoại từ sự kiện
        fixPhone.setDetailsError(event.getDetailsError());  // Đặt chi tiết lỗi từ sự kiện
        fixPhone.setIsDone(event.getIsDone());
        // Lưu đối tượng vào cơ sở dữ liệu

        phoneRepository.save(fixPhone);
        log.error("tao thanh cong phonefix");
        log.error(fixPhone);

    }





    @EventHandler
    public void on(DeletePhoneFixEvent event) {

        log.error("da vao delete event");

        FixPhone fixPhone = phoneRepository.findById(event.getId().toHexString()).orElseThrow(
                () -> new RuntimeException("khoong tim thay phone")
        );
        phoneRepository.delete(fixPhone);
        log.error(" xoa ban ghi thanh cong");

    }


    @EventHandler
    public void on(UpdateStatusPhoneFixEvent event) {
        FixPhone fixPhone = phoneRepository.findById(event.getPhoneFixId().toHexString()).orElseThrow(
                () -> new RuntimeException("khoong tim thay phone")
        );
        fixPhone.setIsDone(event.getIsDone());
        phoneRepository.save(fixPhone);
    }

}
