package com.demo.cqrseventsourcing.webapiread;

import com.demo.cqrseventsourcing.cqrslibrary.MyService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication(scanBasePackages = "com.demo.cqrseventsourcing")
@Configuration
@EnableSwagger2
public class WebApiReadApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApiReadApplication.class, args);
    }
    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "Web Api read side",
                "Web Api provide read resources",
                "0.0.1-SNAPSHOT",
                "Terms of Service",
                new Contact("Julien Cordier", "", ""),
                "",
                "",
                Collections.emptyList());
    }
}