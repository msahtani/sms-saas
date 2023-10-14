package ma.ensa.smsapi.auth.services;

import lombok.RequiredArgsConstructor;
import ma.ensa.smsapi.auth.exceptions.UserNotFoundException;
import ma.ensa.smsapi.auth.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return Optional
                .of(userRepository.findByEmail(email))
                .orElseThrow(UserNotFoundException::new);
    }
}