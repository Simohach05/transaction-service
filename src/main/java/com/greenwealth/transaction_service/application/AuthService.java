package com.greenwealth.transaction_service.application;

import com.greenwealth.transaction_service.application.dto.AuthRequest;
import com.greenwealth.transaction_service.application.dto.AuthResponse;
import com.greenwealth.transaction_service.domain.model.User;
import com.greenwealth.transaction_service.domain.repository.UserRepository;
import com.greenwealth.transaction_service.infrastructure.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    // Méthod of(Register)
    public AuthResponse register(AuthRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        // 1. crypte thes password before save it
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("USER");

        // 2. save it in database
        repository.save(user);
        String token = jwtService.generateToken(user.getUsername());
        return new AuthResponse(token);
    }
    // method of connection (Login)

    public AuthResponse authenticate(AuthRequest request){
        // find user
        User user = repository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found !"));
        // verfy password (rawd / encoded)
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw  new RuntimeException("Password incorrect !");
        }
        // if are the right user
        String token = jwtService.generateToken(user.getUsername());
        return new AuthResponse(token);
    }

}