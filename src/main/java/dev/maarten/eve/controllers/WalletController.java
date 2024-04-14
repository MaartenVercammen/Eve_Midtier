package dev.maarten.eve.controllers;

import dev.maarten.eve.clients.EveWalletClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("wallet")
@RequiredArgsConstructor
@Slf4j
public class WalletController {

    private final EveWalletClient walletClient;

    @GetMapping("balance")
    public double getBalance(Principal principal) {
        var id = principal.getName();
        return walletClient.getCurrentIsk(id);
    }

}
