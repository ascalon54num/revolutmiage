package fr.univlorraine.miage.revolutmiage;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RevolutmiageApplication {

	public static void main(String[] args) {
		SpringApplication.run(RevolutmiageApplication.class, args);
	}

	@Bean
	public OpenAPI banqueAPI() {
		return new OpenAPI().info(new Info()
				.title("banque API")
				.version("1.0")
				.description("Documentation sommaire de API Banque 1.0"));
	}
}
