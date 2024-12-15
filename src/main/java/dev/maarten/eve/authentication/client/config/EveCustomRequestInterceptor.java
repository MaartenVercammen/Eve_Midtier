package dev.maarten.eve.authentication.client.config;

import dev.maarten.eve.authentication.model.AccessToken;
import dev.maarten.eve.authentication.model.TokenStatus;
import dev.maarten.eve.authentication.service.AuthService;
import dev.maarten.eve.core.services.MemoryService;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RequiredArgsConstructor
@Slf4j
public class EveCustomRequestInterceptor implements RequestInterceptor {

    private final MemoryService memoryService;
    private final AuthService authService;

    @Override
    public void apply(RequestTemplate template) {
            AccessToken token = memoryService.getAccessToken();
            if (TokenStatus.EXPIRED.equals(token.getTokenStatus())) {
                token = authService.refreshToken(token);
            }
            template.header("Authorization", "Bearer " + token.getAccessToken());
    }

}
