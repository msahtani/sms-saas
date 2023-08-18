package ma.ensa.smsapi.services;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import ma.ensa.smsapi.dto.sms.SMS;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class SseService {

    private final Map<String, SseEmitter> sses = new HashMap<>();

    private SseEmitter createSSE(
            final String address,
            final int port
    ){
        final var emitter = new SseEmitter(0L);

        emitter.onError((ex) -> {
            log.error("error: {}", ex.getMessage());
            emitter.complete();
        });

        emitter.onCompletion(() -> {
            log.info(
                    " {}:{} : SSE connection is completed from the server",
                    address, port
            );
            sses.remove(address);
        });

        return emitter;
    }

    public SseEmitter add(HttpServletRequest request) throws Exception {

        final var address = request.getRemoteAddr();
        final var port = request.getRemotePort();

        var emitter = createSSE(address, port);

        sses.put(address, emitter);
        emitter.send("");

        log.info(
                "received a SSE connection from {}:{}",
                address, port
        );

        return emitter;
    }

    public void send(SMS sms) throws IOException {

        // send to the all (actually it's just one connected device in the map)
        for(SseEmitter sse: sses.values()){
            sse.send(sms);
        }

    }
}