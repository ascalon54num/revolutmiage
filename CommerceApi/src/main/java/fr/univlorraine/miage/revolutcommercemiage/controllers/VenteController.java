package fr.univlorraine.miage.revolutcommercemiage.controllers;

import fr.univlorraine.miage.revolutcommercemiage.Forms.PaiementForm;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.RoundRobinLoadBalancer;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class VenteController {

    RestTemplate template;
    LoadBalancerClientFactory clientFactory;

    public VenteController (RestTemplate rt, LoadBalancerClientFactory lbcf) {
        this.template = rt;
        this.clientFactory = lbcf;
    }

    @CircuitBreaker(name = "commerce-service", fallbackMethod = "fallbackPaiementVente")
    @PostMapping("/ventes")
    public ResponseEntity<?> paiementVente(@RequestBody PaiementForm body){
        System.out.println(clientFactory);
        RoundRobinLoadBalancer lb = clientFactory.getInstance("banque-service", RoundRobinLoadBalancer.class);
        ServiceInstance instance = lb.choose().block().getServer();
        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/cartes/validate";
        ResponseEntity response = template.postForEntity(url,body,ResponseEntity.class);
        System.out.println(response.getBody());
        return ResponseEntity.ok().build();
    }

    private ResponseEntity<?> fallbackPaiementVente(RuntimeException re){
        System.out.println("Dans fallback");
        return ResponseEntity.status(503).body("Unable to access the service");
    }
}
