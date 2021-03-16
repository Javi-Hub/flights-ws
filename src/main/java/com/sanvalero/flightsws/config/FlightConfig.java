package com.sanvalero.flightsws.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.webmvc.api.OpenApiActuatorResource;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Creado por @author: Javier
 * el 15/03/2021
 */
@Configuration
public class FlightConfig {

    @Bean
    public OpenAPI customOpençAPI(){
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Flights Information")
                                .description("Ejercicio OpenAPI Flights")
                                .contact(new Contact()
                                        .name("Javi")
                                        .email("javi@mail.com")
                                        .url("https://datos.codeandcoke.com"))
                                .version("1.0"));
    }

}
