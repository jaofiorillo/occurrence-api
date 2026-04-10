package com.carbigdata_api.modules.auth.controller;

import com.carbigdata_api.modules.auth.dto.JwtResponse;
import com.carbigdata_api.modules.auth.dto.LoginRequest;
import com.carbigdata_api.modules.auth.services.AutenticacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AutenticacaoService autenticacaoService;

    @PostMapping("/login")
    public JwtResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return autenticacaoService.authenticateUser(loginRequest);
    }
}
