package ma.ensa.smsapi.repositories;

import ma.ensa.smsapi.models.SentSMS;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SmsRepository
        extends JpaRepository<SentSMS, Long> {}
