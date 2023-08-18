package ma.ensa.smsapi.dto.sms;

import lombok.Data;

@Data
public class SmsApi<T> {

    private Auth auth;

    private T data;

}