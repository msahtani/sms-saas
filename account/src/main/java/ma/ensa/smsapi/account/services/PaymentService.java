package ma.ensa.smsapi.account.services;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import ma.ensa.smsapi.account.dto.PaymentDTO;
import ma.ensa.smsapi.account.repositories.PaymentRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static ma.ensa.smsapi.account.services.Auths.*;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    private DateTimeFormatter formatter;

    @PostConstruct
    public void init(){
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }

    public List<PaymentDTO> getAllPayments(int p){

        var account = getUser().getAccount();
        AtomicLong i = new AtomicLong(p);

        return paymentRepository
                .findAllByAccount(account, PageRequest.of(p, 10))
                .stream().map(
                        payment -> PaymentDTO.builder()
                                .id(i.incrementAndGet())
                                .value(payment.getValue())
                                .method(payment.getMethod())
                                .paidAt(formatter.format(payment.getPaidAt()))
                                .build()
                ).toList();

    }

    public void redeemPromoCode(){

    }

}