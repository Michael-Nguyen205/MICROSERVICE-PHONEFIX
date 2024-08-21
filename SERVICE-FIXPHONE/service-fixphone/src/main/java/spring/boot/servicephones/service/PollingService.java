package spring.boot.servicephones.service;


import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import spring.boot.servicephones.command.model.Message;
import spring.boot.servicephones.command.model.MessageRepository;

import java.util.List;
import java.util.concurrent.ExecutionException;


@Log4j2
@Component
public class PollingService {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;


    @Autowired
    private MessageRepository messageRepository;

    @Scheduled(fixedDelay = 1000)
    public void producer() {
        List<Message> messageList = messageRepository.findByStatus(false);
        for (Message message : messageList) {


            try {
                // Chờ đợi cho đến khi message được gửi thành công hoặc thất bại
                SendResult<String, Object> result = kafkaTemplate.send("notification", message).get();
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


    @Scheduled(fixedDelay = 60000)
    public void cleanUpMessage() {
        List<Message> messageList = messageRepository.findByStatus(true);
        if (!messageList.isEmpty()) {
            messageRepository.deleteAll(messageList);
            log.info("Cleaned up {} messages", messageList.size());
        }    }
}
