package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                .requestMatchers("/user").hasRole("USER")
                .requestMatchers("/admin").hasRole("ADMIN")
                    .requestMatchers("/").permitAll()
                    .anyRequest().authenticated()
            )
            .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
            .logout(logout ->
                logout
                    .permitAll()
            );
        return http.build();
    }

	@Bean
	public UserDetailsService userDetailsService() {
		//password for user is "user", admin is "admin"
		UserDetails user = User.withUsername("user").password("$2a$12$bt0VdcxH3kVHAj5Rlys1OepDnjPEet.rDxi.vjx0skQRiW3AzUNGq").roles("USER").build();
		UserDetails admin = User.withUsername("admin").password("$2a$12$gFyejoQB1lYvvErGKIfYHORQ1XqmWueyzquLzy0rovZ3ltFOH3zp2").roles("ADMIN").build();
		return new InMemoryUserDetailsManager(user, admin);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
}
