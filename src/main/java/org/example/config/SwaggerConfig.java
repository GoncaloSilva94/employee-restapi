package org.example.config;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {


    //API documentation
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.example.rest.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());

    }

    //API Info
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("Employee API")
                .description("Spring Boot Rest Api")
                .version("1.0")
                .contact(contact())
                .build();
    }

    //Contact Info
    private Contact contact(){
        return new Contact("Gonçalo Silva", "", "goncalosilva947@gmail.com");
    }
}
