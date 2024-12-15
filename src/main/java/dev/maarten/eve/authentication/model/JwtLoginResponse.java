package dev.maarten.eve.authentication.model;

import lombok.Builder;

@Builder
public record JwtLoginResponse(
        String jwtToken,
        String refreshToken

) {
}
