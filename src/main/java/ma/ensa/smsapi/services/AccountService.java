package ma.ensa.smsapi.services;

import lombok.RequiredArgsConstructor;
import ma.ensa.smsapi.exceptions.UserNotFoundException;
import ma.ensa.smsapi.models.Account;
import ma.ensa.smsapi.models.User;
import ma.ensa.smsapi.repositories.AccountRepository;
import ma.ensa.smsapi.repositories.UserRepository;
import ma.ensa.smsapi.utils.Generator;
import org.springframework.stereotype.Service;

import static ma.ensa.smsapi.services.Auths.getUser;
import static ma.ensa.smsapi.utils.Generator.generateRandomPassword;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;


    public Account getAccount(){

        return accountRepository
                .findByUser(getUser())
                .stream()
                .peek(
                        account -> {
                            if(account.isDisabled()){
                                throw new RuntimeException("you have been suspended");
                            }
                        }
                ).findFirst()
                .orElseThrow(RuntimeException::new);

    }

    public Account createAccount(User user){

        Account account = Account.builder()
                .user(user)
                .build();

        return accountRepository.save(account);

    }

    public Account createAccount(long userId){

        User user = userRepository
                .findById(userId)
                .orElseThrow(UserNotFoundException::new);

        return createAccount(user);
    }

    public String changeAuthToken(String accountSid){

        Account account = accountRepository
                .findById(accountSid)
                // TODO: need to create a custom exception (HTTP 404 NOT FOUND)
                .orElseThrow(RuntimeException::new);

        if(!account.isEnabled()){
            throw new RuntimeException("..........");
        }

        if(account.getUser().getId() != 0){
            throw new RuntimeException(" ............... ");
        }

        String newAuthToken = generateRandomPassword(36);

        account.setAuthToken(newAuthToken);
        accountRepository.save(account);

        return newAuthToken;
    }



}