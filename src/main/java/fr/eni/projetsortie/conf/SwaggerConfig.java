package fr.eni.projetsortie.conf;

import fr.eni.projetsortie.ProjetsortieApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
    private final static Logger logger = LoggerFactory.getLogger(ProjetsortieApplication.class);
    @Bean
    public Docket api() {
        logger.info("Loading swagger config");
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("fr.eni.projetsortie.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Projet sortir API",
                "API of projet sortir",
                "0.0.1-ALPHA",
                "Terms of service",
                new Contact("Pierre Boudeaud", "www.eni.fr", "pierre.boudeaud2017@campus-eni.fr"),
                "License of API", "API license URL", Collections.emptyList());
    }
}
