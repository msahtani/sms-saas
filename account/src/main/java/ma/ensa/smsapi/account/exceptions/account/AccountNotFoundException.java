package ma.ensa.smsapi.account.exceptions.account;

public class AccountNotFoundException extends RuntimeException{

    public AccountNotFoundException(){
        super("account not found");
    }

}
