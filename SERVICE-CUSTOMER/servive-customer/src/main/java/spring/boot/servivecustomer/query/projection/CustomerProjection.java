package spring.boot.servivecustomer.query.projection;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.axonframework.queryhandling.QueryHandler;
import org.checkerframework.checker.units.qual.C;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import spring.boot.commonservice.query.GetDetailPhoneCommonQuery;
import spring.boot.commonservice.query.PhoneResponeCommonModel;

import org.springframework.beans.BeanUtils;
import spring.boot.servivecustomer.command.model.Customer;
import spring.boot.servivecustomer.command.model.CustomerRepository;
import spring.boot.servivecustomer.query.model.CustomerResponeModel;
import spring.boot.servivecustomer.query.query.GetCustomerDetailsQuery;

import java.time.Duration;


@Log4j2
@RequiredArgsConstructor
@Component
public class CustomerProjection {
    private final CustomerRepository customerRepository;

    
    @QueryHandler
    public Mono<CustomerResponeModel> handle(GetCustomerDetailsQuery query) {
        log.error("Query received: {}", query);

        // Tìm kiếm khách hàng bằng ID và xử lý kết quả không đồng bộ
        return customerRepository.findById(query.getId())
                .doOnNext(customer -> log.info("Customer info: {}", customer))
                .delayElement(Duration.ofSeconds(5))// Ghi log thông tin khách hàng khi có kết quả
                .map(customer -> {
                    // Chuyển đổi đối tượng Customer thành CustomerResponeModel
                    CustomerResponeModel model = new CustomerResponeModel();
                    BeanUtils.copyProperties(customer, model);
                    log.info("CustomerResponseModel: {}", model);
                    log.info("Customer info: {}", customer);
                    return model;
                })
                .doOnError(e -> log.error("Error retrieving customer", e)) // Ghi log lỗi nếu có
                .defaultIfEmpty(new CustomerResponeModel()); // Trả về model rỗng nếu không tìm thấy customer
    }

//    @QueryHandler
//    public Mono<CustomerResponeModel> handle(GetCustomerDetailsQuery query) {
//        log.error("Query received: {}", query);
//        return customerRepository.findById(query.getId())
//                .map(customer -> {
//                    CustomerResponeModel model = new CustomerResponeModel();
//                    // Copy properties from Customer to CustomerResponeModel
//                    BeanUtils.copyProperties(customer, model);
//                    log.error("CustomerResponseModel: {}", model);
//                    return model;
//                })
//                .doOnError(e -> log.error("Error retrieving customer", e))
//                .defaultIfEmpty(new CustomerResponeModel()); // Return an empty model if customer is not found
//    }

//
//    @QueryHandler
//    public Mono<CustomerResponeModel> handle(GetCustomerDetailsQuery query) {
//
//        log.error("Query received: {}", query);
//
//        Customer customerr =customerRepository.findById(query.getId());
//
//        log.info("customer info: {}", customerr);
//
//        return customerRepository.findById(query.getId())
//                .flatMap(customer -> Mono.just(convertToResponseModel(customer)))
//                .doOnError(e -> logError("Error retrieving customer", e))
//                .defaultIfEmpty(new CustomerResponeModel()); // Return an empty model if customer is not found
//    }
//
//    private CustomerResponeModel convertToResponseModel(Customer customer) {
//        log.info("customer info: {}", customer);
//        CustomerResponeModel model = new CustomerResponeModel();
//        BeanUtils.copyProperties(customer, model);
//        log.info("CustomerResponseModel: {}", model);
//        return model;
//    }
//
//    private void logError(String message, Throwable e) {
//        log.info(message, e);
//    }



    //Đảm bằng findById thực sự trả về một phần tử và không
    // phải là một Mono.empty(). Nếu Mono.empty()
    // được trả về, doOnNext và map sẽ không được gọi.

}
