package br.edu.unievangelica.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(-20)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
                .formLogin().loginPage("/login").permitAll()
            .and()
                .requestMatchers().antMatchers("/login", "/oauth/authorize", "/oauth/confirm_access")
            .and()
                .authorizeRequests().anyRequest().authenticated();
        // @formatter:on
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.parentAuthenticationManager(authenticationManager);
    }


}



/*
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    Logger log = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
            .and()
                .authorizeRequests()
                .antMatchers("/**").authenticated()
            .and()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("reader")
                .password("reader")
                .authorities("FOO_READ")
            .and()
                .withUser("writer")
                .password("writer")
                .authorities("FOO_READ", "FOO_WRITE");
    }


}
*/
