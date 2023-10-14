package ma.ensa.smsapi.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserDTO {

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    // just for login
    @JsonProperty(value = "password", access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String token;

    private String sessionId;

}