package ma.ensa.smsapi.repositories;

import ma.ensa.smsapi.models.Account;
import ma.ensa.smsapi.models.TemplateMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TMessageRepository extends JpaRepository<TemplateMessage, Long> {

    Optional<TemplateMessage> findByAccountAndName(Account account, String name);

    boolean existsByAccountAndName(Account account, String name);

}