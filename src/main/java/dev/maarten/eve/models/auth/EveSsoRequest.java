package dev.maarten.eve.models.auth;

import lombok.Builder;

@Builder
public record EveSsoRequest(String grant_type, String code) {
}
