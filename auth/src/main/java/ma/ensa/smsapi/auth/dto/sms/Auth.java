package ma.ensa.smsapi.auth.dto.sms;

import lombok.Data;

@Data
public class Auth {

    private String accountSid;

    private String authToken;
}