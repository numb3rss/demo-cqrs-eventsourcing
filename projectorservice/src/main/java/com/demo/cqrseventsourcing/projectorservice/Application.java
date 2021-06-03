package com.demo.cqrseventsourcing.projectorservice;

import com.demo.cqrseventsourcing.projectorservice.application.ports.ElasticSearchRepository;
import com.demo.cqrseventsourcing.projectorservice.application.usecases.ImportAchievment;
import com.demo.cqrseventsourcing.projectorservice.application.usecases.ImportAchievmentValidation;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class)
                .listeners(new ApplicationShutdown())
                .run(args);
    }

    @Bean(name = "validationUseCase")
    ImportAchievment validationUseCase(final ImportAchievment importAchievment, final ElasticSearchRepository elasticSearchRepository){
        return new ImportAchievmentValidation(importAchievment, elasticSearchRepository);
    }
}
