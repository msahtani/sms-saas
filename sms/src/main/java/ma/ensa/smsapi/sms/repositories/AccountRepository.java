package ma.ensa.smsapi.sms.repositories;

import ma.ensa.smsapi.sms.models.Account;
import ma.ensa.smsapi.sms.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {


    Optional<Account> findByUser(User user);
}