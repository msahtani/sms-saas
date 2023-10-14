package ma.ensa.smsapi.auth.exceptions;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(){
        super("user not found");
    }
}
