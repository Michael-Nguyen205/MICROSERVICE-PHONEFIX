package spring.boot.servicephones.saga;


import lombok.extern.log4j.Log4j2;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.spring.stereotype.Saga;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import spring.boot.commonservice.DTO.MessageDTO;
import spring.boot.commonservice.DTO.PhoneFixDTO;
import spring.boot.commonservice.ENUM.NotificationType;
import spring.boot.commonservice.ENUM.Templates;
import spring.boot.commonservice.command.CreateCustomCommonCommand;
import spring.boot.commonservice.query.GetDetailPhoneCommonQuery;
import spring.boot.commonservice.query.PhoneResponeCommonModel;
import spring.boot.servicephones.command.command.DeletePhoneFixCommand;
import spring.boot.servicephones.command.event.CreatePhoneFixEvent;
import spring.boot.servicephones.command.event.DeletePhoneFixEvent;
//import spring.boot.servicephones.command.dto.MessageDTO;
import spring.boot.servicephones.command.model.PhoneFixRepository;
import spring.boot.servicephones.query.model.GetFixPhoneResponeModel;
import spring.boot.servicephones.query.query.GetFixPhoneQuery;
import spring.boot.servicephones.service.SendEmailService;
import org.modelmapper.ModelMapper;

import java.util.UUID;
@Log4j2
@Saga
public class FixPhoneSaga {
    @Autowired
    private transient CommandGateway commandGateway;
    @Autowired
    private transient QueryGateway queryGateway;

    @Autowired
    private SendEmailService sendEmailService;

    @Autowired
    private PhoneFixRepository phoneFixRepository;

    @Autowired
    private  ModelMapper modelMapper;



    @StartSaga
    @SagaEventHandler(associationProperty = "id")
    private void handle(CreatePhoneFixEvent createPhoneFixEvent){
        try{
            log.error("ddang vao sagaaaaaa");




//            modelMapper.typeMap(OrderDTO.class, Order.class)
//                    .addMappings(mapper -> mapper.skip(Order::setId));

//            FixPhone fixPhone = new FixPhone();
//            try {
//                 fixPhone = phoneFixRepository.findById(createPhoneFixEvent.getIdFixphone().toString())
//                                .orElseThrow(()-> new RuntimeException("khong tim thay ban ghi voi id la " + createPhoneFixEvent.getIdFixphone()));
//            } catch (Exception e) {
//                log.error( createPhoneFixEvent.getIdFixphone(), e);
//                throw e;
//            };

//            FixPhone fixPhone = phoneRepository.findById(new ObjectId(createPhoneFixEvent.getId()))
//                    .orElseThrow(() -> new RuntimeException("khong tim thay ban ghi voi id la " + createPhoneFixEvent.getId()));




            GetDetailPhoneCommonQuery getDetailPhoneCommonQuery = new GetDetailPhoneCommonQuery(createPhoneFixEvent.getPhoneId());
            PhoneResponeCommonModel phoneResponeCommonModel =
                    queryGateway.query( getDetailPhoneCommonQuery,
                            ResponseTypes
                                    .instanceOf( PhoneResponeCommonModel.class )).join();


            log.error("check status phone"+phoneResponeCommonModel);

            if(phoneResponeCommonModel.getEnable() == true) {

                log.error(" phone nay co sua");

//                UpdateStatusBookCommand command = new UpdateStatusBookCommand(event.getBookId(), false,event.getEmployeeId(),event.getId());










                // đang bị mất service nàyyyyyy đang bị mất service nàyyyyyy đang bị mất service nàyyyyyy
                CreateCustomCommonCommand command = new CreateCustomCommonCommand(
                        UUID.randomUUID().toString(),
                        createPhoneFixEvent.getNameCustoms(),
                        createPhoneFixEvent.getPhoneNumber(),
                        false
                );
                try {
                    log.error("Tạo custom command");
                    commandGateway.sendAndWait(command);
                    log.error("Command gửi thành công");
                } catch (Exception e) {
                    log.error("Lỗi khi gửi lệnh CreateCustomCommonCommand", e);
                }





                GetFixPhoneQuery getAllFixPhoneQuery = new GetFixPhoneQuery();
                getAllFixPhoneQuery.setId(createPhoneFixEvent.getIdFixphone().toString());



                GetFixPhoneResponeModel getFixPhoneResponeModels = new GetFixPhoneResponeModel();
                getFixPhoneResponeModels = queryGateway
                        .query(getAllFixPhoneQuery
                                , ResponseTypes.instanceOf(GetFixPhoneResponeModel.class))
                        .join();


                PhoneFixDTO phoneFixDTO = modelMapper.map(getFixPhoneResponeModels, PhoneFixDTO.class);
                phoneFixDTO.setPhoneNumCustomer(getFixPhoneResponeModels.getPhoneNumber());
                phoneFixDTO.setGmailCustomer(getFixPhoneResponeModels.getGmailCustomer());

                try {
                    log.error("delieu gui topic la" +phoneFixDTO.toString());



                    MessageDTO<?> mes =MessageDTO.builder()
                            .message("Đặt đơn sửa điện thoại thành công")
                            .to(createPhoneFixEvent.getGmailCustomer() )
                            .template(Templates.SUCCESS_FIX_PHONE_TEMPLATE.getTemplateName())
                            .type(NotificationType.EMAIL.getType())
                            .data(phoneFixDTO)
                            .build();



                    sendEmailService.sendMessage("notification", mes);
                    log.error("Gửi message thành công");
                } catch (Exception e) {
                    log.error("Lỗi khi gửi mesage", e);
                }





            }
            else {
                throw new Exception("Phone Da Khong Con Dich Vu");
            }

        }catch (Exception e){
            log.error("Saga gap phai loi: {}" + e.getMessage(), e);

//            rollBackPhoneFix(createPhoneFixEvent.getId());
        }
    }

    private void rollBackPhoneFix(ObjectId id ) {

        log.error("rollback lại");

        commandGateway.sendAndWait(new DeletePhoneFixCommand(id));
    }


    @SagaEventHandler(associationProperty = "id")
    @EndSaga
    public void handle(DeletePhoneFixEvent event) {
        System.out.println("BorrowDeletedEvent in Saga for Borrowing Id : {} " +
                event.getId());

        SagaLifecycle.end();
    }



}
