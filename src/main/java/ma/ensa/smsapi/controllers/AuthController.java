package ma.ensa.smsapi.controllers;


import lombok.RequiredArgsConstructor;
import ma.ensa.smsapi.dto.UserDTO;
import ma.ensa.smsapi.services.AuthService;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/login")
    public UserDTO login(@RequestBody UserDTO dto){
        return service.login(dto);
    }

    @PostMapping("/signup")
    @ResponseStatus(CREATED)
    public String signup(@RequestBody UserDTO dto){
        service.signUp(dto);
        return "created successfully";
    }


}