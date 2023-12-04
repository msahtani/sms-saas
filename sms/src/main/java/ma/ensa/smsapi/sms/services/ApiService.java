package ma.ensa.smsapi.sms.services;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import ma.ensa.smsapi.sms.dto.Auth;
import ma.ensa.smsapi.sms.dto.SMS;
import ma.ensa.smsapi.sms.dto.SmsApi;
import ma.ensa.smsapi.sms.models.Account;
import ma.ensa.smsapi.sms.models.SentSMS;
import ma.ensa.smsapi.sms.repositories.SmsRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ApiService {

    private final SseService sseService;

    private final SmsRepository smsRepository;

    private final LoadBalancerClient loadBalancerClient;

    private RestTemplate restTemplate;

    @PostConstruct
    public void init(){
        restTemplate = new RestTemplate();
    }

    private Account getAccount(Auth auth){

        HttpEntity<Auth> entity = new HttpEntity<>(auth);

        var response = restTemplate.exchange(
                getAccountServiceUri() + "/account/check",
                HttpMethod.GET,
                entity,
                Account.class
        );

        if(response.getStatusCode() != HttpStatus.OK){
            throw new RuntimeException("an error was occurred");
        }

        return response.getBody();

    }

    private void registerSentSMS(Account account, SMS sms) {

        var sent = SentSMS.builder()
                .account(account)
                .build();

        BeanUtils.copyProperties(sms, sent);
        smsRepository.save(sent);
    }

    public void sendSMS(SmsApi api) throws IOException {
        var account = getAccount(api.getAuth());
        var sms = api.getSms();
        sseService.send(sms);
        registerSentSMS(account, sms);
    }

    private String getAccountServiceUri(){

        return loadBalancerClient
                .choose("account-service")
                .getUri().toString();

    }

}
