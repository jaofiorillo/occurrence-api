package com.carbigdata_api.modules.auth.dto;

public record JwtResponse(String token, Integer id, String email) {
}
