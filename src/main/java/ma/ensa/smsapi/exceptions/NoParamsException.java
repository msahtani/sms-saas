package ma.ensa.smsapi.exceptions;

public class NoParamsException extends RuntimeException {

    public NoParamsException(){
        super("you must have at least 1 param inside the message");
    }

}