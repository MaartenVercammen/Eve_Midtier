package dev.maarten.eve.services;

import dev.maarten.eve.clients.EveSSoClient;
import dev.maarten.eve.models.auth.EveSsoRequest;
import dev.maarten.eve.models.auth.EveSsoResponse;
import dev.maarten.eve.models.auth.Link;
import dev.maarten.eve.models.contract.JwtLoginRequest;
import dev.maarten.eve.models.contract.JwtLoginResponse;
import dev.maarten.eve.models.contract.LoginLinkContract;
import dev.maarten.eve.models.enums.Schema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService {

    private final EveSSoClient client;
    @Value("${auth.domain}")
    private String domain;
    @Value("${auth.path}")
    private String path;
    @Value("${auth.redirect_uri}")
    private String redirect_uri;
    @Value("${auth.client_id}")
    private String clientId;
    @Value("${auth.scope}")
    private String scope;
    @Value("${auth.state}")
    private String state;

    public String getAccessTokenFromEveSso(String code) {
        EveSsoRequest ssoRequest = new EveSsoRequest("authorization_code", code);
        var response = client.getAccessTokenFromEveSso(ssoRequest);
        log.info("Response: {}", response);
        return response.access_token();
    }

    /*
    Link looks like https://login.eveonline.com/v2/oauth/authorize/&scope=esi-characters.read_blueprints.v1&state=<unique-string>
     */
    @Override
    public LoginLinkContract getLoginLink() {
        Link loginLink = Link.builder()
                .schema(Schema.HTTP.getValue())
                .domain(domain)
                .path(path)
                .parameters(getParametersForLoginLink())
                .build();
        return LoginLinkContract.builder().url(loginLink.getLink()).build();
    }

    @Override
    public JwtLoginResponse retrieveJwtFromEve(JwtLoginRequest jwtLoginRequest) {
        if (!jwtLoginRequest.state().equals(state)) throw new RuntimeException("Code has been tampered with");
        EveSsoRequest eveSsoRequest = EveSsoRequest.builder()
                .code(jwtLoginRequest.code())
                .grant_type("authorization_code")
                .build();
        log.info("Calling eve with code {}", eveSsoRequest.code());
        EveSsoResponse response = client.getAccessTokenFromEveSso(eveSsoRequest);
        return JwtLoginResponse.builder()
                .token(response.access_token())
                .build();
    }

    public Map<String, String> getParametersForLoginLink() {
        Map<String, String> params = new HashMap<>();
        params.put("response_type", "code");
        params.put("redirect_uri", redirect_uri);
        params.put("client_id", clientId);
        params.put("scope", scope.replaceAll(" ", "%20"));
        params.put("state", state);
        return params;
    }
}
