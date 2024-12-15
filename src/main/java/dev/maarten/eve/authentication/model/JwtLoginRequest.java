package dev.maarten.eve.authentication.model;

public record JwtLoginRequest(
        String code,
        String state
) {
}
