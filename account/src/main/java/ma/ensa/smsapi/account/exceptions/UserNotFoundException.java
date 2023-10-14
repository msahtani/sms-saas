package ma.ensa.smsapi.account.exceptions;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(){
        super("user not found");
    }
}
