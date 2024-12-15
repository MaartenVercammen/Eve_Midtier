package dev.maarten.eve.authentication.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AccessToken {
        private TokenStatus tokenStatus;
        private String accessToken;
        private String refreshToken;

}