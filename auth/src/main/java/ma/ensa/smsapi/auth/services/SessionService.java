package ma.ensa.smsapi.auth.services;

import ma.ensa.smsapi.auth.models.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static ma.ensa.smsapi.auth.utils.Generator.*;

@Service
public class SessionService {

    private final Map<String, User> sessions;

    public SessionService(){
        this.sessions = new HashMap<>();
    }

    public String add(User user){
        String sessionId = generateRandom(10);
        sessions.put(sessionId, user);
        return sessionId;
    }

    public User get(String sessionId){

        if(sessionId == null || sessionId.isBlank()){
            throw new RuntimeException("session lost ... logging out");
        }

        if(!sessions.containsKey(sessionId)){
            throw new RuntimeException("session expired ... logging out");
        }

        return sessions.remove(sessionId);
    }

    public void remove(String sessionId){
        sessions.remove(sessionId);
    }

}
