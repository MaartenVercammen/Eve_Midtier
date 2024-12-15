package dev.maarten.eve.authentication.client.config;

import feign.Retryer;

public class CustomRetryer extends Retryer.Default {

    public CustomRetryer() {
        super();
    }
}
