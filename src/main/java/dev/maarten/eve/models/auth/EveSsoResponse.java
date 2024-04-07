package dev.maarten.eve.models.auth;

public record EveSsoResponse(String access_token, int expires_in, String refresh_token, String token_type) {
}
