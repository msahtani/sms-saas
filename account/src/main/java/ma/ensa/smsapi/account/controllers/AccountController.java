package ma.ensa.smsapi.account.controllers;


import lombok.RequiredArgsConstructor;
import ma.ensa.smsapi.account.models.Account;
import ma.ensa.smsapi.account.services.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.util.concurrent.TimeUnit.DAYS;
import static org.springframework.http.CacheControl.*;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService service;



    @GetMapping
    public ResponseEntity<Account> getAccount(){

        var account = service.getAccount();
        return ResponseEntity.ok()
                .cacheControl(maxAge(1, DAYS).mustRevalidate())
                .body(account);
    }

    @PatchMapping
    public String changeAuthToken(){
        return service.changeAuthToken();
    }

}