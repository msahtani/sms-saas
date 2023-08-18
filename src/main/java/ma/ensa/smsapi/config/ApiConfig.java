package ma.ensa.smsapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ApiConfig {

    private static final String WS_API = "http://localhost:8081";

    @Bean
    public WebClient webSocketApi(){
        return WebClient.create(WS_API);
    }

}
