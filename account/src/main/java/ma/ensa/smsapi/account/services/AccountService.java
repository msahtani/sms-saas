package ma.ensa.smsapi.account.services;

import lombok.RequiredArgsConstructor;
import ma.ensa.smsapi.account.exceptions.AccountSuspendedException;
import ma.ensa.smsapi.account.exceptions.UserNotFoundException;
import ma.ensa.smsapi.account.models.Account;
import ma.ensa.smsapi.account.models.User;
import ma.ensa.smsapi.account.repositories.AccountRepository;
import ma.ensa.smsapi.account.repositories.UserRepository;
import org.springframework.stereotype.Service;

import static ma.ensa.smsapi.account.services.Auths.getUser;
import static ma.ensa.smsapi.account.utils.Generator.generateRandom;


@Service
@RequiredArgsConstructor
public class AccountService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;


    public Account getAccount(){

        return accountRepository
                .findByUser(getUser())
                .stream()
                .peek(account -> {
                    if(account.isDisabled()){
                        throw new AccountSuspendedException();
                    }
                })
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

    public Account createAccount(User user){
        Account account = Account.builder()
                .user(user)
                .build();

        return accountRepository.save(account);

    }

    public String changeAuthToken(){

        Account account = getUser().getAccount();

        // check if the account is enabled
        if(!account.isEnabled()){
            throw new RuntimeException("..........");
        }

        /*
        if(account.getUser().getId() != 0){
            throw new RuntimeException("...............");
        }
        */

        var newAuthToken = generateRandom(36);

        account.setAuthToken(newAuthToken);
        accountRepository.save(account);

        return newAuthToken;
    }



}