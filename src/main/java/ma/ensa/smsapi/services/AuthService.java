package ma.ensa.smsapi.services;

import lombok.RequiredArgsConstructor;
import ma.ensa.smsapi.config.auth.JwtService;
import ma.ensa.smsapi.dto.UserDTO;
import ma.ensa.smsapi.exceptions.EmailNotAvailableException;
import ma.ensa.smsapi.models.User;
import ma.ensa.smsapi.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccountService accountService;

    public UserDTO login(UserDTO dto){

        User user = (User) userService.loadUserByUsername(
                dto.getEmail()
        );

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getEmail(), dto.getPassword()
                )
        );

        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setToken(
                jwtService.generateToken(user)
        );

        return dto;
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
        accountService.createAccount(user);

    }


}