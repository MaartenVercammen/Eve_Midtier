package dev.maarten.eve.models.contract;

import lombok.Builder;

@Builder
public record LoginLinkContract(
        String url
) {
}
