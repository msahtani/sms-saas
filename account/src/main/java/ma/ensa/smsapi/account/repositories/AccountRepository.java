package ma.ensa.smsapi.account.repositories;

import ma.ensa.smsapi.account.models.Account;
import ma.ensa.smsapi.account.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {


    Optional<Account> findByUser(User user);
}