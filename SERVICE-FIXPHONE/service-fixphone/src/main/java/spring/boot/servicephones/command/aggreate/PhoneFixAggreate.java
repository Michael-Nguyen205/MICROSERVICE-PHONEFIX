package spring.boot.servicephones.command.aggreate;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.bson.types.ObjectId;
import spring.boot.servicephones.command.command.CreatePhoneFixCommand;
import spring.boot.servicephones.command.command.DeletePhoneFixCommand;
import spring.boot.servicephones.command.command.UpdateStatusPhoneFixCommand;
import spring.boot.servicephones.command.event.CreatePhoneFixEvent;
import spring.boot.servicephones.command.event.DeletePhoneFixEvent;
import spring.boot.servicephones.command.event.UpdateStatusPhoneFixEvent;
@Log4j2
@NoArgsConstructor
@AllArgsConstructor
@Aggregate
public class PhoneFixAggreate {
    @AggregateIdentifier

    private String id;
    private String idFixPhone;
    private String nameCustoms;
    private String phoneId;
    private String phoneNumCustomer;
    private String gmailCustomer;
    private String detailsError;
    private Boolean isDone;




    @CommandHandler
    public  PhoneFixAggreate(CreatePhoneFixCommand createPhoneFixCommand) {
        // Tạo sự kiện từ command
        CreatePhoneFixEvent createPhoneFixEvent = new CreatePhoneFixEvent(
                createPhoneFixCommand.getId(),
                createPhoneFixCommand.getIdFixPhone(),
                createPhoneFixCommand.getNameCustoms(),
                createPhoneFixCommand.getPhoneId(),
                createPhoneFixCommand.getPhoneNumCustomer(),
                createPhoneFixCommand.getGmailCustomer(),
                createPhoneFixCommand.getDetailsError(),
                false
        );
        // Áp dụng sự kiện để cập nhật trạng thái của aggregate
        AggregateLifecycle.apply(createPhoneFixEvent);
    }

    @CommandHandler
    public void handle(DeletePhoneFixCommand command) {

        log.error("rollback xoa phonefix");
        DeletePhoneFixEvent event  = new DeletePhoneFixEvent(command.getId()) ;
        AggregateLifecycle.apply(event);
    }





    @CommandHandler
    public void handle(UpdateStatusPhoneFixCommand command) {
        UpdateStatusPhoneFixEvent event  = new UpdateStatusPhoneFixEvent(  command.getPhoneFixId(),   command.getIsdone()) ;
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(CreatePhoneFixEvent event) {
        this.id = event.getId();
        this.nameCustoms = event.getNameCustoms();
        this.phoneId = event.getPhoneId();
        this.phoneNumCustomer = event.getPhoneId();
        this.detailsError = event.getDetailsError();
        this.isDone = event.getIsDone();
    }

    @EventSourcingHandler
    public void on(UpdateStatusPhoneFixEvent event) {
        this.isDone = event.getIsDone();
    }

    @EventSourcingHandler
    public void on(DeletePhoneFixEvent event) {
        // Handle deletion logic if any
    }

}
