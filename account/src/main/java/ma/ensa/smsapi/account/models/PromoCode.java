package ma.ensa.smsapi.account.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.ensa.smsapi.account.models.enums.PromoType;
import ma.ensa.smsapi.account.utils.Generator;

import static jakarta.persistence.GenerationType.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
public class PromoCode {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    private String code;

    private double value;

    @Enumerated(EnumType.STRING)
    private PromoType type;

    private boolean enabled;

    @PrePersist
    public void init(){
        enabled = true;
        if(code == null){
            code = Generator.generateRandom(10);
        }
    }

}
