package ma.ensa.smsapi.account.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
public class SentSMS {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    private Account account;

    private String phoneNumber;

    private String body;

    @CreationTimestamp
    private LocalDateTime sentAt;

    private double cost;

}
