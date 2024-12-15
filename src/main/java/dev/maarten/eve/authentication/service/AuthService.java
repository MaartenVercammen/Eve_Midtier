package dev.maarten.eve.authentication.service;

import com.fasterxml.jackson.databind.json.JsonMapper;
import dev.maarten.eve.authentication.client.EveSSoClient;
import dev.maarten.eve.authentication.model.AccessToken;
import dev.maarten.eve.authentication.model.EveSsoResponse;
import dev.maarten.eve.authentication.model.Link;
import dev.maarten.eve.authentication.model.TokenStatus;
import dev.maarten.eve.authentication.model.JwtLoginRequest;
import dev.maarten.eve.authentication.model.JwtLoginResponse;
import dev.maarten.eve.authentication.model.LoginLinkContract;
import dev.maarten.eve.authentication.model.Schema;
import dev.maarten.eve.core.services.MemoryService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemoryService memoryService;

    private final EveSSoClient client;
    @Value("${auth.domain}")
    private String domain;
    @Value("${auth.path}")
    private String path;
    @Value("${auth.redirect_uri}")
    private String redirect_uri;
    @Value("${auth.client_id}")
    private String clientId;
    @Value("${auth.state}")
    private String state;

    /*
    Link looks like https://login.eveonline.com/v2/oauth/authorize/&scope=esi-characters.read_blueprints.v1&state=<unique-string>
     */
    public LoginLinkContract getLoginLink() {
        Link loginLink = Link.builder()
                .schema(Schema.HTTP.getValue())
                .domain(domain)
                .path(path)
                .parameters(getParametersForLoginLink())
                .build();
        return LoginLinkContract.builder().url(loginLink.getLink()).build();
    }

    public AccessToken refreshToken(AccessToken token){
        EveSsoResponse response = client.getAccessTokenFromEveSso("grant_type=refresh_token&refresh_token=" + token.getRefreshToken());
        return AccessToken.builder()
                        .accessToken(response.access_token())
                        .refreshToken(response.refresh_token())
                        .tokenStatus(TokenStatus.VALID)
                .build();
    }

    @SneakyThrows
    public JwtLoginResponse retrieveJwtFromEve(JwtLoginRequest jwtLoginRequest) {
        if (!jwtLoginRequest.state().equals(state)) throw new RuntimeException("Code has been tampered with");

        EveSsoResponse response = client.getAccessTokenFromEveSso("grant_type=authorization_code&code=" + jwtLoginRequest.code());
        log.info(new JsonMapper().writeValueAsString(response));
        memoryService.setAccessToken(AccessToken.builder()
                        .tokenStatus(TokenStatus.VALID)
                        .accessToken(response.access_token())
                        .refreshToken(response.refresh_token())
                .build());
        return JwtLoginResponse.builder()
                .jwtToken(response.access_token())
                .refreshToken(response.refresh_token())
                .build();
    }

    public Map<String, String> getParametersForLoginLink() {
        Map<String, String> params = new HashMap<>();
        params.put("response_type", "code");
        params.put("redirect_uri", redirect_uri);
        params.put("client_id", clientId);
        params.put("scope", getScope());
        params.put("state", state);
        return params;
    }

    private String getScope() {
        String scope = "publicData esi-wallet.read_character_wallet.v1";
        return scope.replaceAll(" ", "%20");
    }
}
