package br.edu.unievangelica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
@EnableDiscoveryClient
public class VirtooServiceDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(VirtooServiceDiscoveryApplication.class, args);
		//new SpringApplicationBuilder(VirtooServiceDiscoveryApplication.class).web(true).run(args);
	}
}
