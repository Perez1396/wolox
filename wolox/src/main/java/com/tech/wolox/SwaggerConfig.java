/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tech.wolox;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 *
 * @author Usuario
 */
@Configuration
public class SwaggerConfig {
    private static final String base = "com.tech.wolox.controller";
    private static final String regex = "/.*";
   
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(base))
                .paths(PathSelectors.regex(regex))
                .build().apiInfo(apiPermissionsInfo());
                
    }
    
    private ApiInfo apiPermissionsInfo(){
        return new ApiInfoBuilder().title("Api documentation wolox exercise")
                .description("Wolox techicnal exercise to implement microservices in SpringBoot using data from a Json")
                .contact(new Contact("Wolox"," ", " "))
                .build();
    }
}
