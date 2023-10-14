package ma.ensa.smsapi.sms.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.ensa.smsapi.sms.dto.SmsApi;
import ma.ensa.smsapi.sms.services.ApiService;
import ma.ensa.smsapi.sms.services.SseService;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@RestController
@RequestMapping("/app-gw")
@RequiredArgsConstructor
@Slf4j
public class AppGatewayController {

    private final SseService sseService;
    private final ApiService apiService;

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter emitter(@NotNull HttpServletRequest request) throws Exception {
        return sseService.add(request);
    }


    @GetMapping("/send")
    public void send(@RequestBody SmsApi api) throws IOException {
        apiService.sendSMS(api);
    }

}