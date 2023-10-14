package ma.ensa.smsapi.sms.services;

import ma.ensa.smsapi.sms.models.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class Auths {

    public static User getUser(){

        return (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

    public static long getUserId(){
        return getUser().getId();
    }

}
