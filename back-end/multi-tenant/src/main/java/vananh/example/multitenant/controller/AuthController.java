package vananh.example.multitenant.controller;

import vananh.example.common.payload.request.LoginRequest;
import vananh.example.common.payload.response.AuthResponse;
import vananh.example.multitenant.jwt.JwtTokenProvider;
import vananh.example.multitenant.model.User;
import vananh.example.multitenant.repository.CustomUserDetails;
import vananh.example.multitenant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public AuthResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        // Authentication for user info have requested
        Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        // User info is valid when haven't any exception
        // Set authentication info into Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Return JWT for user
        String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
        return new AuthResponse(jwt);
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        userService.save(user);
        return ResponseEntity.ok(user);
    }
}

