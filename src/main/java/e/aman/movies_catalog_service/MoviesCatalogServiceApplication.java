package e.aman.movies_catalog_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableCircuitBreaker
public class MoviesCatalogServiceApplication {
	
	
	/*
	 * @LoadBalancing -> To know which instances to take
	 * */
	@Bean
	@LoadBalanced
	
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(MoviesCatalogServiceApplication.class, args);
	}

}
