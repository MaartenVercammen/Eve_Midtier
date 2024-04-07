package dev.maarten.eve.clients;

import dev.maarten.eve.models.auth.EveSsoRequest;
import dev.maarten.eve.models.auth.EveSsoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "eve-sso", url = "https://login.eveonline.com")
public interface EveSSoClient {

    @RequestMapping(method = RequestMethod.POST, value = "/oauth/token", consumes = "application/x-www-form-urlencoded")
    EveSsoResponse getAccessTokenFromEveSso(EveSsoRequest request);
}
