package dev.maarten.eve.services;

import dev.maarten.eve.models.contract.JwtLoginRequest;
import dev.maarten.eve.models.contract.JwtLoginResponse;
import dev.maarten.eve.models.contract.LoginLinkContract;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    String getAccessTokenFromEveSso(String code);

    LoginLinkContract getLoginLink();

    JwtLoginResponse retrieveJwtFromEve(JwtLoginRequest jwtLoginRequest);
}
