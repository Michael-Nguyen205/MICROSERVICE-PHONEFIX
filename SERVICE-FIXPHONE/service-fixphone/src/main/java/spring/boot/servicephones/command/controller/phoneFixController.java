package spring.boot.servicephones.command.controller;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.boot.commonservice.DTO.PhoneFixDTO;
import spring.boot.servicephones.command.command.CreatePhoneFixCommand;
import spring.boot.servicephones.command.command.UpdateStatusPhoneFixCommand;

import java.util.UUID;
//@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/phonefix")
public class phoneFixController {

    @Autowired
    private  CommandGateway commandGateway;


    @PostMapping
    public ResponseEntity<?> addPhone(@RequestBody PhoneFixDTO phoneDTO) {

        String commandId = UUID.randomUUID().toString(); // UUID cho Command ID
//        ObjectId phoneFixId = new ObjectId(); // ObjectId cho Dữ Liệu

        String phoneFixId = UUID.randomUUID().toString(); // UUID cho Command ID


        CreatePhoneFixCommand command = new CreatePhoneFixCommand(
                commandId
                ,phoneFixId
                ,phoneDTO.getNameCustoms()
                ,phoneDTO.getPhoneId()
                ,phoneDTO.getPhoneNumCustomer()
                ,phoneDTO.getGmailCustomer()
                ,phoneDTO.getDetailsError()
                ,false);


        try{
            commandGateway.sendAndWait(command);
            return ResponseEntity.ok("thanh cong tao fixphone");

        }catch (Exception e){
            return ResponseEntity.badRequest().body("loi tao phixphone");

        }
    }



@PutMapping
public ResponseEntity<?> updateStatusPhoneFix(@PathVariable ObjectId PhoneFixId) {

    ObjectId objectId = new ObjectId();

    UpdateStatusPhoneFixCommand command = new UpdateStatusPhoneFixCommand(objectId,PhoneFixId, true);
        commandGateway.sendAndWait(command);
        return ResponseEntity.ok("thanh cong tao phone");
    }

}
