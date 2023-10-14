package ma.ensa.smsapi.account.config;

import lombok.RequiredArgsConstructor;
import ma.ensa.smsapi.account.config.auth.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig{


    private final AuthenticationProvider authProvider;

    private final JwtAuthFilter jwtAuthFilter;

    private static final List<String> authorizedEndpoints =
            List.of("/auth/**", "/app-gw/**", "/api/**");

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        http
                .csrf(AbstractHttpConfigurer::disable)
                .headers(AbstractHttpConfigurer::disable)
                .cors(
                        cors -> cors.configurationSource(corsConfigSource())
                )
                .authorizeHttpRequests(
                    auths -> auths
                        .requestMatchers(
                            authorizedEndpoints
                                .stream()
                                .map(AntPathRequestMatcher::new)
                                .toArray(RequestMatcher[]::new)
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(
                        session -> session.sessionCreationPolicy(STATELESS)
                )
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    private CorsConfigurationSource corsConfigSource() {
        final var fhc = new CorsConfiguration();
        fhc.addAllowedOriginPattern("*");
        fhc.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH", "OPTION"));
        fhc.setAllowedHeaders(List.of("*"));
        fhc.setExposedHeaders(List.of("*"));
        fhc.setAllowCredentials(true);

        final var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", fhc);

        return source;
    }


}