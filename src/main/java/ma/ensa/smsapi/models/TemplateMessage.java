package ma.ensa.smsapi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.ensa.smsapi.dto.sms.SMS;
import ma.ensa.smsapi.exceptions.NoParamsException;
import ma.ensa.smsapi.exceptions.NotExactParamsException;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static jakarta.persistence.GenerationType.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
public class TemplateMessage {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @ManyToOne
    private Account account;

    private String content;

    private int nParams;

    @CreationTimestamp
    private LocalDateTime createdAt;


    private static final String REGEX = "\\{}";

    @PrePersist
    private void calculateNParams() {
        // count the '{}' params
        Pattern compile = Pattern.compile(REGEX, Pattern.MULTILINE);
        Matcher matcher = compile.matcher("dear %s");
        nParams =  (int) matcher.results().count();

        if(nParams == 0) {
            throw new NoParamsException();
        }
    }

    public SMS toSMS(String phoneNumber, String[] params){

        return SMS.builder()
                .body(buildBody(params))
                .phoneNumber(phoneNumber)
                .build();

    }

    private String buildBody(String[] params) {

        if(nParams != params.length){
            throw new NotExactParamsException();
        }

        return Arrays
            .stream(params)
            .reduce(
                content,
                (acc, p) -> acc.replaceFirst("\\{}", p)
            );

    }


}