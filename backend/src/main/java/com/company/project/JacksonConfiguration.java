package com.company.project;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.problem.jackson.ProblemModule;

@Configuration
public class JacksonConfiguration {
    @Bean
    public ObjectMapper jacksonObjectMapper() {
        return new ObjectMapper().registerModule(new ProblemModule().withStackTraces(false))
                .registerModule(new JavaTimeModule());
    }
}
