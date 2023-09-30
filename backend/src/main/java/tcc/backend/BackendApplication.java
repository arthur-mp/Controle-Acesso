package tcc.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();

		// Permitir credenciais (cookies, autenticação)
		config.setAllowCredentials(true);

		// Permitir todas as origens
		config.addAllowedOriginPattern("*"); // Use allowedOriginPattern

		// Permitir todos os métodos HTTP
		config.addAllowedMethod("*");

		// Permitir todos os cabeçalhos
		config.addAllowedHeader("*");

		source.registerCorsConfiguration("/**", config);

		return new CorsFilter(source);
	}
}
