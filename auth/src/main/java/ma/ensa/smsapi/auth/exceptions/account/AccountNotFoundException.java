package ma.ensa.smsapi.auth.exceptions.account;

public class AccountNotFoundException extends RuntimeException{

    public AccountNotFoundException(){
        super("account not found");
    }

}
