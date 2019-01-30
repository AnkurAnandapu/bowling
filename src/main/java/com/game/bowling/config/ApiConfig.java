package com.game.bowling.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * API configuration.
 */
@Configuration
@EnableSwagger2
class ApiConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(regex("/bowling.*"))
                .build()
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false);
    }


    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Bowling Game Scorer",
                "Parse the input frames score as string and calculate the score for each frame.",
                "0.0.1",
                "https://en.wikipedia.org/wiki/Ten-pin_bowling",
                new Contact("Ankur Anandapu", "https://www.linkedin.com/in/ankur-anandapu/", "anandapuankur@gmail.com"),
                "test project",
                "no license"
        );
    }
}
