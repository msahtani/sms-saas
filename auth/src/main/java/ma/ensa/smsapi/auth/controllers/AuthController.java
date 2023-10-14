package ma.ensa.smsapi.auth.controllers;


import lombok.RequiredArgsConstructor;
import ma.ensa.smsapi.auth.dto.UserDTO;
import ma.ensa.smsapi.auth.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.concurrent.TimeUnit;

import static org.springframework.http.CacheControl.*;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;
    
    @GetMapping
    public ResponseEntity<UserDTO> getUserSession(
            @RequestParam("sessionId") String sessionId
    ){
        var dto = service.getUserSession(sessionId);

        return ResponseEntity.ok()
                .cacheControl(maxAge(1, TimeUnit.DAYS))
                .body(dto);
    }

    @PostMapping
    public ResponseEntity<Object> login(@RequestBody UserDTO dto){
        String sessionId =  service.login(dto);
        
        return ResponseEntity
                .status(FOUND)
                .location(URI.create("/auth?sessionId="+sessionId))
                .build();
    }


    @PostMapping("/signup")
    @ResponseStatus(CREATED)
    public String signup(@RequestBody UserDTO dto){
        service.signUp(dto);
        return "created successfully";
    }


}