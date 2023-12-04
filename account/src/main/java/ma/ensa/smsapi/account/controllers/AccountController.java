package ma.ensa.smsapi.account.controllers;


import lombok.RequiredArgsConstructor;
import ma.ensa.smsapi.account.models.Account;
import ma.ensa.smsapi.account.services.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
                .cacheControl(maxAge(1, DAYS).cachePrivate().mustRevalidate())
                .body(account);
    }

    @GetMapping("/check")
    public Account checkAuthApi(@RequestBody Account account){
        return service.checkAuthApi(account);
    }

    @PatchMapping
    public String changeAuthToken(){
        return service.changeAuthToken();
    }

}