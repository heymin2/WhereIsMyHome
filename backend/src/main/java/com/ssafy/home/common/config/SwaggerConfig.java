package com.ssafy.home.common.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info().title("API 명세서").description("<h3>박형준 일해라</h3>");
        return new OpenAPI().components(new Components()).info(info);
    }

    @Bean
    public GroupedOpenApi memberApi() {
        return GroupedOpenApi.builder().group("member").pathsToMatch("/member/**").build();
    }

    @Bean
    public GroupedOpenApi authApi() {
        return GroupedOpenApi.builder().group("auth").pathsToMatch("/auth/**").build();
    }

    @Bean
    public GroupedOpenApi boardApi() {
        return GroupedOpenApi.builder().group("board").pathsToMatch("/board/**").build();
    }

    @Bean
    public GroupedOpenApi areaApi() {
        return GroupedOpenApi.builder().group("area").pathsToMatch("/area/**").build();
    }
}
