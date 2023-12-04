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
                .orElseThrow(() -> new RuntimeException("something went wrong #1001"));
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



        var newAuthToken = generateRandom(36);

        account.setAuthToken(newAuthToken);
        accountRepository.save(account);

        return newAuthToken;
    }


    public Account checkAuthApi(Account api) {

        // TODO: create a custom exception
        var account = accountRepository
                .findById(api.getAccountSid())
                .orElseThrow(RuntimeException::new);

        boolean authTokenMatches = // true or false
                account.getAuthToken().equals(api.getAuthToken());

        // TODO: create another custom exception
        if(!authTokenMatches){
            throw new RuntimeException("auth token does not matches");
        }

        return account;
    }
}