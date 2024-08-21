package spring.boot.servicephones.command.aggreate;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import spring.boot.servicephones.command.command.CreatePhoneCommand;
import spring.boot.servicephones.command.event.CreatePhoneEvent;


@AllArgsConstructor
@NoArgsConstructor
@Aggregate
public class PhoneAggreate {
    @AggregateIdentifier
    private String id;
    private String name;
    private Boolean enable;


    @CommandHandler
    public PhoneAggreate(CreatePhoneCommand createPhoneCommand) {
        CreatePhoneEvent createPhoneEvent = new CreatePhoneEvent(
                createPhoneCommand.getId(),
                createPhoneCommand.getName(),
                createPhoneCommand.getEnable()
        );
        AggregateLifecycle.apply(createPhoneEvent);
    }

    @EventSourcingHandler
    public void on(CreatePhoneEvent event) {
        this.id = event.getId();
        this.name = event.getName();
        this.enable = event.getEnable();
    }
}
