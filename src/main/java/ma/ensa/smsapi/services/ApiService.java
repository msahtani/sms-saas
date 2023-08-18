package ma.ensa.smsapi.services;

import lombok.RequiredArgsConstructor;
import ma.ensa.smsapi.dto.sms.Auth;
import ma.ensa.smsapi.dto.sms.SMS;
import ma.ensa.smsapi.dto.sms.SmsApi;
import ma.ensa.smsapi.dto.sms.TemplateSms;
import ma.ensa.smsapi.exceptions.NoSuchTemplateFound;
import ma.ensa.smsapi.models.Account;
import ma.ensa.smsapi.models.SentSMS;
import ma.ensa.smsapi.repositories.AccountRepository;
import ma.ensa.smsapi.repositories.SmsRepository;
import ma.ensa.smsapi.repositories.TMessageRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ApiService {

    private final SseService sseService;

    private final AccountRepository accountRepository;

    private final SmsRepository smsRepository;

    private final TMessageRepository tMessageRepository;

    private Account getAccount(Auth auth){

        var account = accountRepository
                .findById(auth.getAuthToken())
                .orElseThrow(
                        () -> new RuntimeException("ACCOUNT NOT FOUND")
                );

        // TODO: add new features (prev auth, ... )
        if(auth.getAuthToken().equals(account.getAuthToken())){
            throw new RuntimeException("AUTH IS INCORRECT");
        }

        return account;

    }

    private Long registerSentSMS(Account account, SMS<String> sms) {

        var sent = SentSMS.builder()
                .account(account)
                .build();

        BeanUtils.copyProperties(sms, sent);
        return smsRepository
                .save(sent)
                .getId();
    }

    public void sendSMS(SmsApi<SMS> api) throws IOException {
        var account = getAccount(api.getAuth());
        var sms = api.getData();
        sseService.send(sms);
        registerSentSMS(account, sms);
    }

    public void sendTemplatedSMS(SmsApi<TemplateSms> api) throws IOException{

        Account account = getAccount(api.getAuth());

        // get data associated with the request
        var data = api.getData();

        // find the template
        var tMessage = tMessageRepository
                .findByAccountAndName(account, data.getName())
                .orElseThrow(NoSuchTemplateFound::new);

        // apply the template
        SMS sms = tMessage.toSMS(
                data.getPhoneNumber(),
                data.getParams()
        );

        sseService.send(sms);
        registerSentSMS(account, sms);

    }

}
