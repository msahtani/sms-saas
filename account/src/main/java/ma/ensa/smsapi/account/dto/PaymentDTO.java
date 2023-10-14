package ma.ensa.smsapi.account.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.ensa.smsapi.account.models.enums.PaymentMethod;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDTO {

    private long id;

    private PaymentMethod method;

    private double value;

    private String paidAt;

}