package com.show.car.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;


@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    public static final String DEFAULT_INCLUDE_PATTERN = "/.*";

    @Bean
    public Docket swaggerSpringfoxDocket(CarProperties properties) {
        Contact contact = new Contact(properties.getSwagger().getContactName(),
                properties.getSwagger().getContactUrl(), properties.getSwagger().getContactEmail());

        ApiInfo apiInfo = new ApiInfo(properties.getSwagger().getTitle(),
                properties.getSwagger().getDescription(), getClass().getPackage().getImplementationVersion(),
                properties.getSwagger().getTermsOfServiceUrl(), contact, properties.getSwagger().getLicense(),
                properties.getSwagger().getLicenseUrl());

        Docket docket = new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo).forCodeGeneration(true)
                .genericModelSubstitutes(ResponseEntity.class).select().paths(regex(DEFAULT_INCLUDE_PATTERN)).build();
        return docket;
    }
}
