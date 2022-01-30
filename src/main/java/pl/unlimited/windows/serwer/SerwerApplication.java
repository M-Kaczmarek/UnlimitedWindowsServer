package pl.unlimited.windows.serwer;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "API aplikacji wspomagającej precesy logistyczne", version = "2.0", description = "Informacje o dostepnych endpointach"))
@SecurityScheme(
        name = "tokenJWT",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SerwerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SerwerApplication.class, args);
    }

//    @Bean
//    public WebMvcConfigurer corsCofigure() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry
//                        .addMapping("/**")
//                        .allowedOrigins("http://localhost:3000")
//                        .allowedMethods("DELETE", "PUT", "GET", "POST");
//            }
//        };
//    }
}
