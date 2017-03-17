package br.edu.unievangelica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class VirtooServiceDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(VirtooServiceDiscoveryApplication.class, args);
	}
}
