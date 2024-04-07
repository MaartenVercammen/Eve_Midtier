package dev.maarten.eve.models.contract;

public record JwtLoginRequest(
        String code,
        String state
) {
}
