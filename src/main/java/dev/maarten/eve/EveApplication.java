package dev.maarten.eve;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EveApplication {

    public static void main(String[] args) {
        SpringApplication.run(EveApplication.class, args);
    }

}
