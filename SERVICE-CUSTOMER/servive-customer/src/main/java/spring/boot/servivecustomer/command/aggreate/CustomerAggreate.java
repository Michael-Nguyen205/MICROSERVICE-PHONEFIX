package spring.boot.servivecustomer.command.aggreate;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import reactor.core.publisher.Mono;
import spring.boot.commonservice.command.CreateCustomCommonCommand;
import spring.boot.servivecustomer.command.event.CreateCustomerEvent;

@Log4j2
@AllArgsConstructor
@NoArgsConstructor
@Aggregate
public class CustomerAggreate {
    @AggregateIdentifier
    private String idComand;
//    private String id;
    private String name;
    private String phoneNumber;
    private Boolean isBan;


//    @CommandHandler
//    public CustomerAggreate(CreateCustomCommonCommand createCustomCommonCommand) {
//        log.error("info commed",createCustomCommonCommand);
//        CreateCustomerEvent event = new CreateCustomerEvent();
//
//
//        AggregateLifecycle.apply(event);
//    }


    @CommandHandler
    public CustomerAggreate(CreateCustomCommonCommand createCustomCommonCommand) {
        log.info("Received command: {}", createCustomCommonCommand);

        // Sử dụng Mono để xử lý một số logic không đồng bộ
        Mono.just(createCustomCommonCommand)
                .doOnNext(cmd -> log.info("Processing command: {}", cmd))
                .flatMap(cmd -> performSomeAsyncOperation(cmd)) // Giả sử đây là một phương thức trả về Mono<Void>
                .doOnSuccess(result -> {
                    // Tạo sự kiện sau khi hoàn thành công việc không đồng bộ
                    CreateCustomerEvent event = new CreateCustomerEvent(createCustomCommonCommand.getId(), createCustomCommonCommand.getName(), createCustomCommonCommand.getPhoneNumber(), false);
                    // Áp dụng sự kiện
                    AggregateLifecycle.apply(event);
                })
                .doOnError(error -> log.error("Error processing command: {}", error))
                .subscribe(); // Đăng ký và bắt đầu chuỗi xử lý không đồng bộ
    }


    private Mono<Void> performSomeAsyncOperation(CreateCustomCommonCommand cmd) {
        // Giả lập một thao tác không đồng bộ, ví dụ: gọi một dịch vụ từ xa hoặc truy cập cơ sở dữ liệu
        return Mono.fromRunnable(() -> {
            try {
                // Giả lập thời gian xử lý
                Thread.sleep(1000); // Không khuyến khích sử dụng Thread.sleep trong mã thật
                log.info("Async operation completed for command: {}", cmd);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        });
    }

    @EventSourcingHandler
    public void on(CreateCustomerEvent event) {
        this.idComand = event.getId();
        this.name = event.getName();
        this.phoneNumber = event.getPhoneNumber();
        this.isBan = event.getIsBan();
    }



}
