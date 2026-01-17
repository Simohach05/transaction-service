package com.greenwealth.transaction_service.infrastructure.web;


import com.greenwealth.transaction_service.application.AuthService;
import com.greenwealth.transaction_service.application.dto.AuthRequest;
import com.greenwealth.transaction_service.application.dto.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService service;
    @PostMapping("/register")
    public AuthResponse register(@RequestBody AuthRequest request){
        return service.register(request);
    }
    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request){
        return service.authenticate(request);
    }
}
