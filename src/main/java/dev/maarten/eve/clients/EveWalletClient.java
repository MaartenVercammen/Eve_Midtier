package dev.maarten.eve.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "eve-wallet", url = "https://esi.evetech.net/latest/characters/", configuration = EveSsoApi.class)
public interface EveWalletClient {

    @GetMapping("{characterId}/wallet?datasource=tranquility")
    double getCurrentIsk(@PathVariable(name = "characterId") String characterId);

}
