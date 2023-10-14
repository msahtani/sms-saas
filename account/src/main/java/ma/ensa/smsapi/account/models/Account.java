package ma.ensa.smsapi.account.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.*;
import static ma.ensa.smsapi.account.utils.Generator.generateRandom;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
public class Account {

    @Id
    private String accountSid;

    private String authToken;

    @OneToOne
    @JsonProperty( value = "user", access = WRITE_ONLY)
    private User user;

    private double balance;

    @JsonProperty( value = "user", access = WRITE_ONLY)
    private boolean enabled;

    @PrePersist
    public void init(){
        accountSid = generateRandom(36);
        authToken  = generateRandom(36);
        balance = .0;
        enabled = true;
    }

    @JsonProperty( value = "user", access = WRITE_ONLY)
    public boolean isDisabled() {
        return !enabled;
    }
}
