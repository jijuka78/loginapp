package com.xerox.login.loginapp.config;

import java.time.Duration;

import org.modelmapper.ModelMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class LoginAppConfiguration {

	@Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
	
	@Bean 
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
	 
		return builder.setConnectTimeout(Duration.ofMillis(3000))
	  .setReadTimeout(Duration.ofMillis(3000)) .build(); 
	}
	
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) {
//		http.authorizeSecurityRequests(auth -> auth.anyRequest().authenticated());
//		return http.build();
//	}
	 
}

