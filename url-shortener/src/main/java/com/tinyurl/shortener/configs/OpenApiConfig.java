package com.tinyurl.shortener.configs;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList("keycloak"))
                .components(new Components()
                        .addSecuritySchemes("keycloak",
                                new SecurityScheme()
                                        .name("keycloak")
                                        .type(SecurityScheme.Type.OAUTH2)
                                        .flows(new OAuthFlows()
                                                .authorizationCode(new OAuthFlow()
                                                        .authorizationUrl("http://localhost:1113/realms/tinyurl-realm/protocol/openid-connect/auth")
                                                        .tokenUrl("http://localhost:1113/realms/tinyurl-realm/protocol/openid-connect/token")
                                                        .scopes(new Scopes()
                                                                .addString("openid", "OpenID Connect scope")
                                                                .addString("profile", "Profile scope")
                                                        )
                                                )
                                        )
                        )
                );
    }
}

