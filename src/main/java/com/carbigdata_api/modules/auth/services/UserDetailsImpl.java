package com.carbigdata_api.modules.auth.services;

import com.carbigdata_api.modules.cliente.model.Cliente;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Getter
    private final Integer id;
    private final String username;
    @JsonIgnore
    private final String password;

    public UserDetailsImpl(Integer id, String email, String senha) {
        this.id = id;
        this.username = email;
        this.password = senha;
    }

    public static UserDetailsImpl build(Cliente cliente) {

        return new UserDetailsImpl(
            cliente.getId(),
            cliente.getEmail(),
            cliente.getSenha());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
