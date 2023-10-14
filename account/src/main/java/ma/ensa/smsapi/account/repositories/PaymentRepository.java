package ma.ensa.smsapi.account.repositories;

import ma.ensa.smsapi.account.models.Account;
import ma.ensa.smsapi.account.models.Payment;
import ma.ensa.smsapi.account.models.enums.PaymentMethod;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import static ma.ensa.smsapi.account.models.enums.PaymentMethod.*;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findAllByAccount(Account account, Pageable pageable);

    boolean existsPaymentByAccountAndMethod(Account account, PaymentMethod method);

    default boolean isPromoCodeAlreadyUsed(Account account){
        return existsPaymentByAccountAndMethod(
                account, PROMO_CODE
        );
    }

}
