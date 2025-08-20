package com.project.forumhub.web;

import com.project.forumhub.domain.User;
import com.project.forumhub.security.TokenService;
import com.project.forumhub.web.dto.LoginRequest;
import com.project.forumhub.web.dto.LoginResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final TokenService tokenService;

    public AuthController(AuthenticationManager authManager, TokenService tokenService) {
        this.authManager = authManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest req) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.email(), req.password())
        );
        User u = (User) auth.getPrincipal();
        String jwt = tokenService.generate(u);

        return ResponseEntity.ok(new LoginResponse(
                jwt, "Bearer",
                u.getId(), u.getName(), u.getEmail(), u.getRole().name()
        ));
    }
}
