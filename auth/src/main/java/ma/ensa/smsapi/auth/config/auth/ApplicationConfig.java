package ma.ensa.smsapi.auth.config.auth;

import lombok.RequiredArgsConstructor;
import ma.ensa.smsapi.auth.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final UserService userService;

    @Bean
    public AuthenticationProvider authenticationProvider(PasswordEncoder encoder){
        return new DaoAuthenticationProvider(){{
            setUserDetailsService(userService);
            setPasswordEncoder(encoder);
        }};

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception
    {
        return config.getAuthenticationManager();
    }

}