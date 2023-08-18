package ma.ensa.smsapi.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
public class SentSMS {

    @Id
    private Long id;

    @ManyToOne
    private Account account;

    private String phoneNumber;

    private String body;

    @CreationTimestamp
    private LocalDateTime sentAt;

}
