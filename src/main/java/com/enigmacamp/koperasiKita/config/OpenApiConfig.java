package com.enigmacamp.koperasiKita.config;

//import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
//import io.swagger.v3.oas.models.security.SecurityRequirement;
//import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI enigmaCademyOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Koperasi Kita")
                        .description("Aplikasi Koperasi Kita")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("Koperasi Kita")
                                .email("koperasikita@email.com"))
                        .license(new License()
                                .name("Koperasi Kita")
                                .url("www.koperasikita.com")));
    }
}
