package dev.maarten.eve.wallet.clients;

import dev.maarten.eve.authentication.client.config.CustomErrorDecoder;
import dev.maarten.eve.authentication.client.config.CustomRetryer;
import dev.maarten.eve.authentication.client.config.EveCustomRequestInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "eve-wallet", url = "https://esi.evetech.net/latest/characters/", configuration = {CustomRetryer.class, CustomErrorDecoder.class, EveCustomRequestInterceptor.class})
public interface EveWalletClient {

    @GetMapping("{characterId}/wallet?datasource=tranquility")
    double getCurrentIsk(@PathVariable(name = "characterId") String characterId);

}
