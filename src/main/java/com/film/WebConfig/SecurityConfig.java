package com.film.WebConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.film.controller.user.LoadController;
import com.film.models.CustomUserDetails;
import com.film.services.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private UserService userService;
	
	LoadController loadController = new LoadController();
	
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
				requestMatchers("/my-account/**").authenticated().
				requestMatchers("/*").permitAll().
				requestMatchers("/api/comment/**").permitAll().
				requestMatchers("/admin/**").hasAuthority("ADMIN").
				anyRequest().authenticated())
			.formLogin(login -> login
				.loginPage("/login")
				.successHandler((request, response, authentication) -> {
					response.sendRedirect("/");
					CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
					int id = customUserDetails.getId().intValue();
					userService.updateIsActivity(1, "", id);
				})
                .failureHandler((request, response, exception) -> response.sendRedirect("/login?error=true"))
				.usernameParameter("username")
				.passwordParameter("password")).
			logout(logout -> logout
					.logoutUrl("/logout")
					.logoutSuccessHandler((request, response, authentication) -> {
						CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
						int id = customUserDetails.getId().intValue();
						userService.updateIsActivity(0, loadController.dateTime(), id);
			            response.sendRedirect("/login");
			        }))
			.exceptionHandling(exceptionHandling -> exceptionHandling
				.accessDeniedHandler((request, response, accessDeniedException) -> response.sendRedirect("/"))								
			)		
			.oauth2Login(oauth2Customize->oauth2Customize
				.loginPage("/"))
		;
		return httpSecurity.build();
	}
	
	@Bean
	WebSecurityCustomizer customizer() {
		return (web) -> web.ignoring().requestMatchers("/resources/**", "/templates/*", "/static/**", "/css/**", "/js/**", "/myJs/**", "/img/**", "/sass/**", "/fonts/**");
	}
}
