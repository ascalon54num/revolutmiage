package fr.univlorraine.miage.revolutcommercemiage;

import fr.univlorraine.miage.revolutcommercemiage.config.ClientConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@LoadBalancerClients({
		@LoadBalancerClient(name = "banque-service", configuration = ClientConfiguration.class)
})
public class RevolutcommercemiageApplication {

	public static void main(String[] args) {
		SpringApplication.run(RevolutcommercemiageApplication.class, args);
	}

	@Bean
	RestTemplate template() {
		return new RestTemplate();
	}
}
