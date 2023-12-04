package ma.ensa.smsapi.sms;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class SmsApplication {


    public static void main(String[] args) {

        SpringApplication.run(SmsApplication.class, args);

        Flux<Integer> flux = Flux.just(1,2,3,4);

        flux.subscribe(
        );


    }

}
