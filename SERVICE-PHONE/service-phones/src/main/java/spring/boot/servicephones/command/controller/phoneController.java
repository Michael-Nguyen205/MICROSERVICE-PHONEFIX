package spring.boot.servicephones.command.controller;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.boot.servicephones.command.command.CreatePhoneCommand;
import spring.boot.servicephones.command.dto.phoneDTO;
import spring.boot.servicephones.command.model.Phone;
import java.util.UUID;
@RestController
@RequestMapping("/api/v1/phone")
public class phoneController {

    @Autowired
    private CommandGateway commandGateway;

    @PostMapping("addPhone")
    public ResponseEntity<?> addPhone(@RequestBody phoneDTO phoneDTO) {
        CreatePhoneCommand command = new CreatePhoneCommand(
                UUID.randomUUID().toString(),
                phoneDTO.getName(),
                phoneDTO.getEnable()
        );
        commandGateway.sendAndWait(command);
        return ResponseEntity.ok("thanh cong tao phone");
    }
}

