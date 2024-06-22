package com.company.project;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.extensions.ExtensionProperty;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        extensions = {
                @Extension(name = "awesomegardning", properties = {
                    @ExtensionProperty(name = "type", value = "awesomegardening")
                })
        },
        info = @Info(
                title = "Plant API",
                version = "1.0.0.0",
                description = "API for managing plants",
                contact = @Contact(
                    name = "Awesome Gardening",
                    url = "https://awesomegardening.com",
                    email = "info@awesomegardning.com"
                )
        )
    )
@Configuration
public class OpenAPIConfiguration {
}
