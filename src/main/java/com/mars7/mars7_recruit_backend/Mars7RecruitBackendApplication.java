package com.mars7.mars7_recruit_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Mars7RecruitBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(Mars7RecruitBackendApplication.class, args);
    }

}
