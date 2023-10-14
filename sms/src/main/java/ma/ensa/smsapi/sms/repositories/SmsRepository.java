package ma.ensa.smsapi.sms.repositories;

import ma.ensa.smsapi.sms.models.SentSMS;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SmsRepository
        extends JpaRepository<SentSMS, Long> {}
