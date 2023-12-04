package ma.ensa.smsapi.apigateway;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.stereotype.Component;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.gateway.route.RouteLocator;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class Routes implements RouteLocator {

    private final RouteLocatorBuilder builder;

    private Function<PredicateSpec, Buildable<Route>> predicate(String path, String uri){
        return r-> r
                .path(path)
                .uri(uri);
    }

    @Override
    public Flux<Route> getRoutes() {
        return builder.routes()
                .route(
                        "auth",
                        predicate("/auth/**", "lb://auth-service/auth")
                ).route(
                        "account",
                        predicate("/account/**", "lb://account-service/account")
                ).route(
                        "sms",
                        predicate("/sms/**", "lb://sms-service")
                )
                .build().getRoutes();
    }
}
