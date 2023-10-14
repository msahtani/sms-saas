package ma.ensa.smsapi.sms.exceptions;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(){
        super("user not found");
    }
}
