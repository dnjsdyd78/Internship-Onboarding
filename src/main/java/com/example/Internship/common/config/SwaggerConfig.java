package com.example.Internship.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

    @Bean
    public OpenAPI openAPI() {
        Server server = new Server();
        server.setUrl("/");

        Info info = new Info()
                .title("Internship API")
                .version("1.0")
                .description("Security Api Test");

        return new OpenAPI()
                .info(info)
                .servers(List.of(server));
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080")  // 모든 포트를 허용
                .allowedMethods("OPTIONS", "GET", "POST", "PUT", "DELETE");
    }


}
