package ma.ensa.smsapi.account.repositories;

import ma.ensa.smsapi.account.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String username);

    boolean existsByEmailOrPhoneNumber(String email, String phoneNumber);

    boolean existsByEmail(String email);

}