package ma.ensa.smsapi.auth.exceptions;

public class NoParamsException extends RuntimeException {

    public NoParamsException(){
        super("you must have at least 1 param inside the message");
    }

}