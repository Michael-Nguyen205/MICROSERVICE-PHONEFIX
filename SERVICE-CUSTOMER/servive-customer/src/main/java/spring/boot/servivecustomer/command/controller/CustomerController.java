//package spring.boot.servivecustomer.command.controller;
//
//import org.axonframework.commandhandling.gateway.CommandGateway;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//
//import java.util.UUID;
//@RestController
//@RequestMapping("/api/v1/customer")
//public class CustomerController {
//
//    @Autowired
//    private CommandGateway commandGateway;
//
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
//}
//
