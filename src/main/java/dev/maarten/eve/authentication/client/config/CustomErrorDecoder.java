package dev.maarten.eve.authentication.client.config;

import dev.maarten.eve.authentication.model.AccessToken;
import dev.maarten.eve.authentication.model.TokenStatus;
import dev.maarten.eve.core.services.MemoryService;
import feign.FeignException;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class CustomErrorDecoder implements ErrorDecoder {

    private final MemoryService memoryService;
    private final static long retryAfter = 5000L;

    @Override
    public Exception decode(String methodKey, Response response) {
        int status = response.status();
        if (status == 401 || status == 403) {
            FeignException exception = feign.FeignException.errorStatus(methodKey, response);
            AccessToken accessToken = memoryService.getAccessToken();
            accessToken.setTokenStatus(TokenStatus.EXPIRED);
            return new RetryableException(
                    response.status(),
                    exception.getMessage(),
                    response.request().httpMethod(),
                    exception,
                    retryAfter,
                    response.request());
        }
        return feign.FeignException.errorStatus(methodKey, response);
    }
}