package ma.ensa.smsapi.account.exceptions;

public class AccountSuspendedException extends RuntimeException {

    public AccountSuspendedException(){
        super("you have been suspended");
    }


}
