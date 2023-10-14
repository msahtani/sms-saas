package ma.ensa.smsapi.sms.services;

import lombok.RequiredArgsConstructor;
import ma.ensa.smsapi.sms.dto.Auth;
import ma.ensa.smsapi.sms.dto.SMS;
import ma.ensa.smsapi.sms.dto.SmsApi;
import ma.ensa.smsapi.sms.models.Account;
import ma.ensa.smsapi.sms.models.SentSMS;
import ma.ensa.smsapi.sms.repositories.AccountRepository;
import ma.ensa.smsapi.sms.repositories.SmsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ApiService {

    private final SseService sseService;

    private final AccountRepository accountRepository;

    private final SmsRepository smsRepository;

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


}
