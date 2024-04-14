package dev.maarten.eve.clients;

import dev.maarten.eve.services.MemoryService;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RequiredArgsConstructor
@Slf4j
public class EveSsoApi implements RequestInterceptor {

    private final MemoryService memoryService;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("Authorization", "Bearer " + memoryService.getJwt());
    }
}
