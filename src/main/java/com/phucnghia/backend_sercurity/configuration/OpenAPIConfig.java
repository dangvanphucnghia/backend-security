package com.phucnghia.backend_sercurity.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;


@Configuration
@Profile("!prod")
public class OpenAPIConfig {

    @Bean
    public GroupedOpenApi publicApi(@Value("${openapi.service.api-docs}") String apiDocs) {
        return GroupedOpenApi.builder()
                .group(apiDocs) // /v3/api-docs/backend-security
                .packagesToScan("com.tayjava.controller")
                .build();
    }

    @Bean
    public OpenAPI openAPI(
            @Value("${openapi.service.title}") String title,
            @Value("${openapi.service.version}") String version,
            @Value("${openapi.service.serverUrl}") String serverUrl,
            @Value("${openapi.service.serverName}") String serverName) {
        return new OpenAPI()
                .servers(List.of(new Server().url(serverUrl).description(serverName)))
                .info(new Info().title(title)
                        .description("Backend Security Training Course has designed by TayJava")
                        .version(version)
                        .license(new License().name("Apache 2.0").url("https://springdoc.org")));
    }
}
