package com.film.WebConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
		// TODO Auto-generated method stub
		httpSecurity
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests((auth) -> auth.
				requestMatchers("/my-account").authenticated().
				requestMatchers("/cap-nhat-biet-danh").authenticated().
				requestMatchers("/*").permitAll().
				requestMatchers("/api/comment/**").permitAll().
				requestMatchers("/admin/**").hasAuthority("ADMIN").
				anyRequest().authenticated())
			.formLogin(login -> login.
				loginPage("/login").
				loginProcessingUrl("/login").
				usernameParameter("username").
				passwordParameter("password").
				defaultSuccessUrl("/admin/", true)).logout(logout->logout.logoutUrl("/logout").logoutSuccessUrl("/login")).
				logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login"))
			.exceptionHandling(exceptionHandling -> exceptionHandling
				.accessDeniedHandler((request, response, accessDeniedException) -> response.sendRedirect("/"))								
			)		
			.oauth2Login(oauth2Customize->oauth2Customize
				.loginPage("/login-google"))
		;
		return httpSecurity.build();
	}
	
	@Bean
	WebSecurityCustomizer customizer() {
		return (web) -> web.ignoring().requestMatchers("/resources/**", "/templates/*", "/static/**", "/css/**", "/js/**", "/img/**", "/sass/**", "/fonts/**");
	}
}
