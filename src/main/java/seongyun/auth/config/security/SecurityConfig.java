package seongyun.auth.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
@Configuration
public class SecurityConfig {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//@Bean//FOR test
	public MapReactiveUserDetailsService userDetailService() {
		@SuppressWarnings("deprecation")
		UserDetails user = User.withDefaultPasswordEncoder()
				.username("user")
				.password("user")
				.roles("user")
				.build();
		return new MapReactiveUserDetailsService(user);
	}
	
	@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
		return http
//				.headers().xssProtection()
				.csrf().disable()
				.authorizeExchange()
				.pathMatchers("/api/**", "/static/**").permitAll()
				.anyExchange()
				.authenticated()
				.and().formLogin()
				.and().httpBasic()
				.and().build();
	}
}
