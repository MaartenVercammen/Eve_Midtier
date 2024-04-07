package dev.maarten.eve.models.contract;

import lombok.Builder;

@Builder
public record JwtLoginResponse(
        String token
) {
}
