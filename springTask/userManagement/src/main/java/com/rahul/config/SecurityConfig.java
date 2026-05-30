package com.rahul.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.rahul.security.JwtFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
	
	 private final JwtFilter jwtFilter;

	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

	    	http.cors(cors -> {});
	        http.csrf(csrf -> csrf.disable());
	        
	        http.headers(headers -> headers
	                .frameOptions(frame -> frame.disable())
	        );

	        http.authorizeHttpRequests(auth -> auth
	        		 .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
	                .requestMatchers("/auth/**").permitAll()
	                .requestMatchers("/h2-console/**").permitAll()//
	                .requestMatchers("/admin/**").hasRole("Admin")
	                .requestMatchers("/manager/**").hasRole("Manager")
	                .requestMatchers("/user/**").authenticated()
	                .anyRequest().authenticated()
	        );

//	        http.headers(headers -> headers.frameOptions(frame -> frame.disable()));

	        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

	        return http.build();
	    }
	    
	    @Bean
	    public WebMvcConfigurer corsConfigurer() {
	        return new WebMvcConfigurer() {

	            @Override
	            public void addCorsMappings(CorsRegistry registry) {

	                registry.addMapping("/**")
	                        .allowedOrigins("http://localhost:5173")
	                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
	                        .allowedHeaders("*");
	            }
	        };
	    }

	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	    @Bean
	    public AuthenticationManager authenticationManager(
	            AuthenticationConfiguration config
	    ) throws Exception {
	        return config.getAuthenticationManager();
	    }

}
