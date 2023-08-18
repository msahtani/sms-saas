package ma.ensa.smsapi.dto.sms;

import lombok.Data;

@Data
public class TemplateSms {

    private String phoneNumber;

    private String name;
    private String[] params;

}
