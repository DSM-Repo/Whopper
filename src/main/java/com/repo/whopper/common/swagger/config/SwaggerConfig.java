package com.repo.whopper.common.swagger.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import io.swagger.v3.core.jackson.ModelResolver;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList("JWT"))
                .components(new Components().addSecuritySchemes("JWT", createAPIKeyScheme()))
                .info(apiInfo());
    }

    @Bean
    public ModelResolver modelResolver(ObjectMapper objectMapper) {
        return new ModelResolver(objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE));
    }

    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }

    private Info apiInfo() {
        return new Info()
                .title("Spring Boot REST API Specifications")
                .description("Specification")
                .version("1.0.0");
    }

    @Bean
    public GroupedOpenApi getAuthApi() {
        return createGroupedOpenApi("auth", "/auth/**");
    }

    @Bean
    public GroupedOpenApi getFeedbackApi() {
        return createGroupedOpenApi("feedback", "/feedback/**");
    }

    @Bean
    public GroupedOpenApi getFileApi() {
        return createGroupedOpenApi("file", "/file/**");
    }

    @Bean
    public GroupedOpenApi getHistoryApi() {
        return createGroupedOpenApi("history", "/history/**");
    }

    @Bean
    public GroupedOpenApi getLibraryApi() {
        return createGroupedOpenApi("library", "/library/**");
    }

    @Bean
    public GroupedOpenApi getMajorApi() {
        return createGroupedOpenApi("major", "/major/**");
    }

    @Bean
    public GroupedOpenApi getNoticeApi() {
        return createGroupedOpenApi("notice", "/notice/**");
    }

    @Bean
    public GroupedOpenApi getResumeApi() {
        return createGroupedOpenApi("resume", "/resume/**");
    }

    @Bean
    public GroupedOpenApi getStudentApi() {
        return createGroupedOpenApi("student", "/user/**");
    }

    @Bean
    public GroupedOpenApi getTeacherApi() {
        return createGroupedOpenApi("teacher", "/teacher/**");
    }

    private GroupedOpenApi createGroupedOpenApi(String groupName, String path) {
        return GroupedOpenApi.builder()
                .group(groupName)
                .pathsToMatch(path)
                .build();
    }
}
