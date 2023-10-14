package ma.ensa.smsapi.account.services;

import ma.ensa.smsapi.account.models.User;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;

public class Auths {

    public static User getUser(){

        return (User) getContext()
                .getAuthentication()
                .getPrincipal();
    }

    public static long getUserId(){
        return getUser().getId();
    }

}
