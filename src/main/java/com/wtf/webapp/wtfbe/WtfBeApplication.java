package com.wtf.webapp.wtfbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class WtfBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(WtfBeApplication.class, args);
    }

}
