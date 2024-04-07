package dev.maarten.eve.controllers;

import dev.maarten.eve.models.contract.JwtLoginRequest;
import dev.maarten.eve.models.contract.JwtLoginResponse;
import dev.maarten.eve.models.contract.LoginLinkContract;
import dev.maarten.eve.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/login/link")
    public LoginLinkContract getLoginLink() {
        return authService.getLoginLink();
    }

    @PostMapping("/login/code")
    public JwtLoginResponse retrieveJwtFromEve(@RequestBody JwtLoginRequest jwtLoginRequest) {
        return authService.retrieveJwtFromEve(jwtLoginRequest);
    }
}
