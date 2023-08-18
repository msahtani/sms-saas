package ma.ensa.smsapi.controllers;

import lombok.RequiredArgsConstructor;
import ma.ensa.smsapi.dto.sms.SmsApi;
import ma.ensa.smsapi.services.ApiService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {

    private final ApiService service;


    @PostMapping("/send")
    public String sendSMS(@RequestBody SmsApi sms) throws IOException {
        service.sendSMS(sms);
        return "";

    }


}
