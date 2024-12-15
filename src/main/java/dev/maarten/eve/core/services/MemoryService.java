package dev.maarten.eve.core.services;

import dev.maarten.eve.authentication.model.AccessToken;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
public class MemoryService {

    private AccessToken accessToken;

}
