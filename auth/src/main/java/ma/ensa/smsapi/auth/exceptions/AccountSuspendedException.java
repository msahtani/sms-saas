package ma.ensa.smsapi.auth.exceptions;

public class AccountSuspendedException extends RuntimeException {

    public AccountSuspendedException(){
        super("you have been suspended");
    }


}
