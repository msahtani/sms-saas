package ma.ensa.smsapi.sms.dto;

import lombok.Data;

@Data
public class Auth {

    private String accountSid;

    private String authToken;
}