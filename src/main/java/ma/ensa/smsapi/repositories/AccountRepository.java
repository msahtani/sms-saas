package ma.ensa.smsapi.repositories;

import ma.ensa.smsapi.models.Account;
import ma.ensa.smsapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {


    Optional<Account> findByUser(User user);
}