package spring.boot.servicephones.command.model;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MessageRepository extends MongoRepository<Message, String> {


    List<Message>  findByStatus(Boolean status);

}
