package com.film.WebConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.film.controller.user.LoadController;
import com.film.models.CustomUserDetails;
import com.film.services.UserService;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

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
				requestMatchers("/tu-bao-cac").authenticated().
				requestMatchers("/*").permitAll().
				requestMatchers("/api/comment/**").permitAll().
				requestMatchers("/admin/**").permitAll().
				requestMatchers(request -> {
                    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                    if (authentication == null || !authentication.isAuthenticated()) {
                        return false;
                    }
                    Object principal = authentication.getPrincipal();
                    if (principal instanceof CustomUserDetails) {
                        CustomUserDetails userDetails = (CustomUserDetails) principal;
                        return userDetails.isEnabled();
                    }
                    return false;
                }).authenticated()
				.anyRequest().authenticated())			
			.formLogin(login -> login
				.loginPage("/login")
				.successHandler((request, response, authentication) -> {
					response.sendRedirect("/");
					CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
					int id = customUserDetails.getId().intValue();
					userService.updateIsActivity(1, "", id);
				})
				.failureHandler((request, response, exception) -> {
			        String redirectUrl = "/login?error=true";
			        if (exception instanceof DisabledException) {
			            redirectUrl = "/login?message=Your account is not activated.";
			        }
			        response.sendRedirect(redirectUrl);
			    })
				.usernameParameter("username")
				.passwordParameter("password"))
			.logout(logout -> logout
					.logoutUrl("/logout")
					.logoutSuccessHandler((request, response, authentication) -> {
						CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
						int id = customUserDetails.getId().intValue();
						userService.updateIsActivity(0, loadController.dateTime(), id);
			            response.sendRedirect("/login");
			        }))
			.exceptionHandling(exceptionHandling -> exceptionHandling
		            .accessDeniedHandler((request, response, accessDeniedException) -> response.sendRedirect("/")))	
			.oauth2Login(oauth2Customize->oauth2Customize
				.loginPage("/"))
		;
		return httpSecurity.build();
	}
	
	@Bean
	WebSecurityCustomizer customizer() {
		return (web) -> web.ignoring().requestMatchers("/resources/**", "/templates/**", "/static/**", "/css/**", "/js/**", "/myJs/**", "/img/**", "/sass/**", "/fonts/**");
	}
}
