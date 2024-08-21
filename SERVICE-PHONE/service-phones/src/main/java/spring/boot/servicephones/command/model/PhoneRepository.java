package spring.boot.servicephones.command.model;

import org.springframework.data.mongodb.repository.MongoRepository;
public interface PhoneRepository extends MongoRepository<Phone, String> {
}
