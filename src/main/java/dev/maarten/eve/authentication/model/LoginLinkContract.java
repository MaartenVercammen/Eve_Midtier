package dev.maarten.eve.authentication.model;

import lombok.Builder;

@Builder
public record LoginLinkContract(
        String url
) {
}
