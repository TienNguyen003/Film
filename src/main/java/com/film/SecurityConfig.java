package com.film;

import java.io.IOException;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
		httpSecurity.authorizeRequests((auth) -> auth.
				requestMatchers("/*").permitAll().
				requestMatchers("/admin/**").hasAuthority("ADMIN").
				anyRequest().authenticated())
			.csrf(csrf -> csrf.disable())
//			.oauth2Login(oauth2Customize->oauth2Customize
//				.loginPage("/login-google"))
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
					.loginProcessingUrl("/login-google")
	                .loginPage("/oauth2/authorization/google")
	                .successHandler(new AuthenticationSuccessHandler() {
						
						@Override
						public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
								Authentication authentication) throws IOException, ServletException {
							// TODO Auto-generated method stub
							request.authenticate(response);
						}
					})
	        )
		;
		return httpSecurity.build();
	}
	
	@Bean
	WebSecurityCustomizer customizer() {
		return (web) -> web.ignoring().requestMatchers("/resources/**", "/templates/**", "/static/**", "/css/**", "/js/**", "/img/**", "/sass/**", "/fonts/**");
	}
}
