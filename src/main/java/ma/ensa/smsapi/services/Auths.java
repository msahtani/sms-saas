package ma.ensa.smsapi.services;

import ma.ensa.smsapi.models.User;
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
