package ma.ensa.smsapi.sms.exceptions;

public class EmailNotAvailableException extends RuntimeException {

    public EmailNotAvailableException(){
        super("email or phone number not available");
    }

}