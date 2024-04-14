package dev.maarten.eve.config;

import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public JsonMapper createMapper() {
        return new JsonMapper();
    }


}
