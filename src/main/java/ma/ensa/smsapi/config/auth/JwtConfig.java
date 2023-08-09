package ma.ensa.smsapi.config.auth;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Date;


@Configuration
@ConfigurationProperties("jwt")
@Data
public class JwtConfig {

    private String secretKey;
    private Long duration;

    public Long getDuration() {
        // convert duration from days to milliseconds
        return duration * 86_400_000;
    }

    public Date getExperationDate() {
        return new Date(
                System.currentTimeMillis()
                        + getDuration()
        );
    }

}