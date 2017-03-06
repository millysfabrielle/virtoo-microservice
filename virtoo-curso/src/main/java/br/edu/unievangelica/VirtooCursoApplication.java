package br.edu.unievangelica;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@EnableResourceServer
@RestController
@EnableFeignClients
//@EnableOAuth2Sso
public class VirtooCursoApplication {

	@Autowired
	private CursoClient cursoClient;

	@RequestMapping("/")
	public Message home() {
		return new Message("Novo item");
	}

	@RequestMapping("/items")
	public List<Message> items() {
		List<Message> list = cursoClient.getItem();
		list.forEach(item -> {
			item.setContent("FEIGN");
		});
		return list;
	}

	public static void main(String[] args) {
		SpringApplication.run(VirtooCursoApplication.class, args);
	}

	@Bean
	public RequestInterceptor requestTokenBearerInterceptor() {
		return new RequestInterceptor() {
			@Override
			public void apply(RequestTemplate requestTemplate) {

				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				String name = auth.getName(); //get logged in username

				System.out.println("AUTH = " + auth);

				System.out.println("------- NAME = " + name);


				System.out.println("------- " + SecurityContextHolder.getContext());

				OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails)
						SecurityContextHolder.getContext().getAuthentication().getDetails();

				requestTemplate.header("Authorization", "bearer " + details.getTokenValue());
			}
		};
	}

}