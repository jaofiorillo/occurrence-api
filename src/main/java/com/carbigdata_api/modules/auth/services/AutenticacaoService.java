package com.carbigdata_api.modules.auth.services;

import com.carbigdata_api.config.exceptions.ValidationException;
import com.carbigdata_api.modules.auth.dto.JwtResponse;
import com.carbigdata_api.modules.auth.dto.LoginRequest;
import com.carbigdata_api.modules.auth.jwt.JwtUtils;
import com.carbigdata_api.modules.cliente.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AutenticacaoService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final ClienteRepository clienteRepository;

    public JwtResponse authenticateUser(LoginRequest loginRequest) {
        try {
            var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.senha()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            var jwt = jwtUtils.generateJwtToken(authentication);
            var userDetails = (UserDetailsImpl) authentication.getPrincipal();

            return new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername());
        } catch (BadCredentialsException ex) {
            throw new ValidationException("Credenciais inválidas");
        }
    }
}
