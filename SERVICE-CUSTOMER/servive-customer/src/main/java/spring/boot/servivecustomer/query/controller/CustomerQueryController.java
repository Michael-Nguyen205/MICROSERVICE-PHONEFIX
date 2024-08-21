package spring.boot.servivecustomer.query.controller;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import spring.boot.servivecustomer.query.model.CustomerResponeModel;
import spring.boot.servivecustomer.query.query.GetCustomerDetailsQuery;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerQueryController {


    @Autowired
    private QueryGateway queryGateway;


    @GetMapping("/getDetailCustomer/{id}")
    public Mono<ResponseEntity<CustomerResponeModel>> getDetailCustomer(@PathVariable String id) {
        GetCustomerDetailsQuery query = new GetCustomerDetailsQuery(id);
        return Mono.fromFuture(() -> queryGateway.query(query, CustomerResponeModel.class))
                .map(result -> ResponseEntity.ok(result))
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new CustomerResponeModel())));
    }


    @GetMapping("/test")
    public Mono<ResponseEntity<String>> getTest() {
        return Mono.fromFuture(() -> CompletableFuture.supplyAsync(() ->
                        ResponseEntity.ok("hello")))
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Error occurred")));

}


//    @GetMapping("/getDetailCustomer/{id}")
//    public Mono<ResponseEntity<CustomerResponeModel>> getDetailCustomer(@PathVariable String id) {
//        GetCustomerDetailsQuery query = new GetCustomerDetailsQuery(id);
//        return Mono.defer(() -> Mono.fromFuture(() -> queryGateway.query(query, CustomerResponeModel.class)))
//                .map(result -> ResponseEntity.ok(result))
//                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                        .body(new CustomerResponeModel())));
//    }



//    @PostMapping
//    public ResponseEntity<?> addPhone(@RequestBody phoneDTO phoneDTO) {
//        CreatePhoneCommand command = new CreatePhoneCommand(
//                UUID.randomUUID().toString(),
//                phoneDTO.getName(),
//                phoneDTO.getEnable()
//        );
//        commandGateway.sendAndWait(command);
//        return ResponseEntity.ok("thanh cong tao phone");
//    }




}

