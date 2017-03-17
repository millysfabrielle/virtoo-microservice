package br.edu.unievangelica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
@RestController
@EnableResourceServer
@EnableEurekaClient
public class VirtooResourceApplication {

	@RequestMapping("/")
	public List<Message> home() {
		List<Message> list = new ArrayList<>();
		list.add(new Message("Item 1"));
		list.add(new Message("Item 2"));
		list.add(new Message("Item 3"));
		list.add(new Message("Item 4"));

		return list;
	}


	public static void main(String[] args) {
		SpringApplication.run(VirtooResourceApplication.class, args);
	}

}

class Message {
	private String id = UUID.randomUUID().toString();
	private String content;

	Message() {
	}

	public Message(String content) {
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public String getContent() {
		return content;
	}
}
