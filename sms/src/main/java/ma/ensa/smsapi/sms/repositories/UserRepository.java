package ma.ensa.smsapi.sms.repositories;


import ma.ensa.smsapi.sms.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String username);
    boolean existsByEmailOrPhoneNumber(String email, String phoneNumber);
}