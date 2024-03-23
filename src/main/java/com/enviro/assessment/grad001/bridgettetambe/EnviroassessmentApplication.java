package com.enviro.assessment.grad001.bridgettetambe;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@OpenAPIDefinition(info = @Info(title = " Enviro assessment API"))
@SpringBootApplication
@EnableScheduling
public class EnviroassessmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnviroassessmentApplication.class, args);
    }

}
