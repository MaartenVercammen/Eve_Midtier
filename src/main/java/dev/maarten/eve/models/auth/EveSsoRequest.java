package dev.maarten.eve.models.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record EveSsoRequest(
        @JsonProperty("grant_type")
        String grant_type,
        @JsonProperty("code")
        String code) {
}
