package ma.ensa.smsapi.auth.exceptions;

public class NotExactParamsException extends RuntimeException {

    public NotExactParamsException(){
        super("you must provide the same exact params");
    }

}