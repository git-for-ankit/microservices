package com.example.microservice.gatewayservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
    public SavedRequestAwareAuthenticationSuccessHandler successHandler() {
        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setDefaultTargetUrl("/sample-service/welcome");
        successHandler.setAlwaysUseDefaultTargetUrl(Boolean.TRUE);
        return successHandler;
    }
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER).and()
		.httpBasic().disable()
		.authorizeRequests()
		.antMatchers("/eureka/**").hasRole("ADMIN")
		.antMatchers("/style/**", "/images/**", "/js/**","/login","/sample-service/**").permitAll()
		.anyRequest().authenticated().and()
		.formLogin().loginPage("/login").successHandler(successHandler()).permitAll().and()
		.logout().permitAll().and()
		.csrf().disable();
	}
	@Autowired 
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.inMemoryAuthentication().withUser("admin").password("{noop}admin").roles("SYSTEM"); 
	}

}


