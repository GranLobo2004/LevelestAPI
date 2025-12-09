package es.zenith.levelestapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
public class CORSConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();

        // 🔥 Permitir tu web en HTTPS
        config.setAllowedOrigins(List.of("https://localhost:3443"));

        // Métodos permitidos
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        // Cabeceras permitidas
        config.setAllowedHeaders(List.of("Authorization", "Content-Type"));

        // Necesario para cookies + JWT HttpOnly
        config.setAllowCredentials(true);

        // Tiempo de cacheo del preflight
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
