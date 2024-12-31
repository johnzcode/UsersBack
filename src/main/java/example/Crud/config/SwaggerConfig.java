package example.Crud.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;

import java.util.List;

public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("API de gestión de usuarios")
                        .version("1.0").description("API Rest")
                        .contact(new Contact().name("Johnz")
                                .email("johnzcode@gmail.com")))
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Server local")
                ));
    }
}
