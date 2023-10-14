package ma.ensa.smsapi.account.controllers;

import lombok.RequiredArgsConstructor;
import ma.ensa.smsapi.account.dto.PaymentDTO;
import ma.ensa.smsapi.account.services.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping
    public List<PaymentDTO> getAllPayments(
            @RequestParam(value = "p", required = false) Integer p
    ){
        if(p == null) p = 0;
        return paymentService.getAllPayments(p);
    }

}
