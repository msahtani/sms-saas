package ma.ensa.smsapi.auth.services;

import lombok.RequiredArgsConstructor;
import ma.ensa.smsapi.auth.config.auth.JwtService;
import ma.ensa.smsapi.auth.dto.UserDTO;
import ma.ensa.smsapi.auth.exceptions.EmailNotAvailableException;
import ma.ensa.smsapi.auth.models.User;
import ma.ensa.smsapi.auth.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {


    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SessionService sessionService;


    // TODO: check also the phone number, if the phone number is inserted
    // then return implicitly the email for the authentication
    public boolean checkEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public String login(UserDTO dto){

        // will certainly return the user object
        // because it's already checked in the
        // previous step
        User user = userRepository
                .findByEmail(dto.getEmail());

        // authenticate
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getEmail(), dto.getPassword()
                )
        );

        return sessionService.add(user);
    }



    public void signUp(UserDTO dto) {

        // check the availability of username and email
        if (
                userRepository.existsByEmailOrPhoneNumber(
                        dto.getEmail(),
                        dto.getPhoneNumber()
                )
        ) throw new EmailNotAvailableException();

        User user = new User();
        BeanUtils.copyProperties(dto, user);
        user.encodePassword(passwordEncoder);
        user = userRepository.save(user);

        // TODO: change this line later


    }


    public UserDTO getUserSession(String sessionId) {

        UserDTO dto = new UserDTO();

        User user = sessionService.get(sessionId);

        // copy all properties (except the password) to the dto object
        BeanUtils.copyProperties(user, dto);

        // generate the JWT token
        var token = jwtService.generateToken(user);
        dto.setToken(token);
        dto.setSessionId(sessionId);

        return dto;
    }
}