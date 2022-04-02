package com.intercop.retail.ms.client.msclient.config.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {

    @Value("${ms.client.version}")
    private String msClientVersion;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("")
                .directModelSubstitute(LocalDateTime.class, String.class)
                .directModelSubstitute(LocalDate.class, String.class)
                .directModelSubstitute(LocalTime.class, String.class)
                .directModelSubstitute(ZonedDateTime.class, String.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.intercop.retail.ms.client.msclient"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiEndPointsInfo())
                .enable(true)
                ;
    }

    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("MS-CLIENTS")
                .description("Backend to manage clients.\n\n"
                        + "Content: \n"
                        + "\t* Services to manage clients\n"
                        + "\t* Perform data calculations\n\n"
                        + "Technologies: \n"
                        + "\t* Microservice developed with Java +  Spring Boot\n"
                        + "\t* API Rest documented in Swagger\n"
                        + "\t* Implemented in Heroku and code uploaded in GITHUB\n\n"
                   )
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .version("1.1")
                .build();
    }
}