package ma.ensa.smsapi.controllers;


import lombok.RequiredArgsConstructor;
import ma.ensa.smsapi.models.Account;
import ma.ensa.smsapi.services.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService service;

    @GetMapping
    public Account getAccount(){
        return service.getAccount();
    }


}