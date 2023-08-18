package ma.ensa.smsapi.dto.sms;

import lombok.Data;

@Data
public class Auth {

    private String accountSid;

    private String authToken;
}