package com.carbigdata_api.modules.auth.services;

import com.carbigdata_api.modules.cliente.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final ClienteRepository clienteRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var cliente = clienteRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Cliente não encontrado."));

        return UserDetailsImpl.build(cliente);
    }

}
