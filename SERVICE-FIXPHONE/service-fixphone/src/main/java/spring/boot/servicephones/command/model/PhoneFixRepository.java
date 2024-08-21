package spring.boot.servicephones.command.model;


import org.springframework.data.mongodb.repository.MongoRepository;

public interface PhoneFixRepository extends MongoRepository<FixPhone, String> {


//    Optional<FixPhone> findByPhoneId(String phoneId);


}
