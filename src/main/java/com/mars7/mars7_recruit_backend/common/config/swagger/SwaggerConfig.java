package com.mars7.mars7_recruit_backend.common.config.swagger;

import com.mars7.mars7_recruit_backend.common.dto.ApiResponse;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.utils.SpringDocUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;
@Configuration
public class SwaggerConfig {

//    static {
//
//        SpringDocUtils.getConfig().replaceWithClass(Object.class, ApiResponse.class);
//    }

    @Bean
    public OpenAPI openAPI() {
        String jwt = "JWT";
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwt);
        Components components = new Components().addSecuritySchemes(jwt, new SecurityScheme()
                .name(jwt)
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
        );
       
        Server productionServer = new Server();
        productionServer.setUrl("http://125.133.62.199:26900");
        productionServer.setDescription("Mars7 배포 서버");

        Server localServer = new Server();
        localServer.setUrl("http://localhost:8080");
        localServer.setDescription("로컬 테스트 서버");

        return new OpenAPI()
                .info(apiInfo())
                .addSecurityItem(securityRequirement)
                .components(components)
                .servers(List.of(productionServer, localServer)); // servers 추가
    }

    private Info apiInfo() {
        return new Info()
                .title("Mars7 동미동락")
                .description("동미동락 swagger")
                .version("1.0.0");
    }
}
