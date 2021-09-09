package pl.wojcik.stripeinvoices.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

import static java.util.Collections.singletonList;

@Configuration
@EnableSwagger2
@EnableCaching
public class Config {

    @Bean
    public Docket swaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
//                .ignoredParameterTypes(UsernamePasswordAuthenticationToken.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("pl.wojcik.stripeinvoices.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(createApiInfo())
                .securitySchemes(singletonList(createSchema()))
                .securityContexts(singletonList(createContext()));
    }

    private SecurityContext createContext() {
        return SecurityContext.builder()
                .securityReferences(createRef())
                .forPaths(PathSelectors.any())
                .build();
    }

    private List<SecurityReference> createRef() {
        AuthorizationScope authorizationScope = new AuthorizationScope(
                "global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return singletonList(new SecurityReference("apiKey", authorizationScopes));
    }

    private SecurityScheme createSchema() {
        return new ApiKey("apiKey", "Authorization", "header");
    }

    private ApiInfo createApiInfo() {
        return new ApiInfo("Spring Boot REST API",
                "Spring Boot 2021",
                "version: 1.00",
                "",
                new Contact("Krzyszof W.",
                        "https://www.linkedin.com/in/krzysztof-w%C3%B3jcik-2344a1192/",
                        ""),
                "License: PUBLIC.",
                "",
                Collections.emptyList());
    }
}
