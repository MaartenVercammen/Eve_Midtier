package dev.maarten.eve.clients;

import dev.maarten.eve.models.auth.EveSsoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "eve-sso", url = "https://login.eveonline.com", configuration = EveSsoClientConfig.class)
public interface EveSSoClient {

    @PostMapping(value = "/v2/oauth/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    EveSsoResponse getAccessTokenFromEveSso(@RequestBody String request);
}
