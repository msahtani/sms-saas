package ma.ensa.smsapi.sms.services;

import lombok.RequiredArgsConstructor;
import ma.ensa.smsapi.sms.exceptions.UserNotFoundException;
import ma.ensa.smsapi.sms.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository
                .findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
    }
}