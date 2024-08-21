package spring.boot.servicephones.service;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
//import spring.boot.servicephones.command.dto.MessageDTO;
import spring.boot.commonservice.DTO.MessageDTO;
import spring.boot.servicephones.command.model.Message;
import spring.boot.servicephones.command.model.MessageRepository;

import java.util.concurrent.ExecutionException;

@Log4j2
@Service
public class SendEmailService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

//    private final   KafkaTemplate<String, Object> kafkaTemplate;
//
//    @Autowired
//    public sendEmailService(KafkaTemplate<String, Object> kafkaTemplate) {
//
//        this.kafkaTemplate = kafkaTemplate;
//    }


    public void sendMessage(String topic, MessageDTO<?> messageDTO) {
        // Save the message with initial status false
        Message<?> message = modelMapper.map(messageDTO, Message.class);
        message.setData(messageDTO.getData());
        message.setStatus(false);

        try {
            // Chờ đợi cho đến khi message được gửi thành công hoặc thất bại
            SendResult<String, Object> result = kafkaTemplate.send(topic, messageDTO).get();
            message.setStatus(true);
            messageRepository.save(message);
            log.error("Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "] with padition  ["+ result.getRecordMetadata().partition()+"]  ");
        } catch (ExecutionException | InterruptedException e) {
            messageRepository.save(message);
            Thread.currentThread().interrupt();
            log.error("Failed to send Kafka message", e);
        }
    }
}










//    public void sendMessage(String topic, MessageDTO messageDTO) {
//        kafkaTemplate.send(topic, messageDTO);
//
//
//        try {
//            kafkaTemplate.send(topic, messageDTO).get();  // Chờ đợi cho đến khi message được gửi thành công hoặc thất bại
//            // If sending message is successful, update status to true
//            Message message = modelMapper.map(messageDTO, Message.class  );
//            message.setStatus(true);
//            messageRepository.save(message);
//
//
//        } catch (ExecutionException | InterruptedException e) {
//            // Handle failure case if needed
//            Thread.currentThread().interrupt();
//            log.error("Failed to send Kafka message", e);
//            Message message = modelMapper.map(messageDTO, Message.class  );
//            message.setStatus(false);
//            messageRepository.save(message);
//
//
//        }



//        for (int i = 0; i < 100; i++) {
//        kafkaTemplate.send(topic, messageDTO).addCallback(new KafkaSendCallBack <String, Object>(){
//                @Override
//                public void onSuccess(SendResult<String, Object> result) {
//                    // Xử lý khi gửi tin nhắn thành công
//                    log.info("Gửi tin nhắn thành công: offset = {}", result.getRecordMetadata().offset());
//                }
//                @Override
//                public void onFailure(Throwable ex) {
//                    // Xử lý khi gửi tin nhắn không thành công
//                    log.error("Gửi tin nhắn thất bại", ex);
//                }
//            });
//        }







//

