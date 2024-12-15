package dev.maarten.eve.authentication.controller;

import dev.maarten.eve.authentication.model.JwtLoginRequest;
import dev.maarten.eve.authentication.model.JwtLoginResponse;
import dev.maarten.eve.authentication.model.LoginLinkContract;
import dev.maarten.eve.authentication.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class AuthController {

    private final AuthService authService;

    @GetMapping("/link")
    public LoginLinkContract getLoginLink() {
        return authService.getLoginLink();
    }

    @PostMapping("/code")
    public JwtLoginResponse retrieveJwtFromEve(@RequestBody JwtLoginRequest jwtLoginRequest) {
        return authService.retrieveJwtFromEve(jwtLoginRequest);
    }
}
