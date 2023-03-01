package seongyun.auth.config.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AuthenticationManager implements ReactiveAuthenticationManager{
	private final ReactiveUserDetailsService ruds;
	private final PasswordEncoder pe;
	
	@PostConstruct
	public void aa() {
		System.out.println(pe.encode("ADMIN"));
	}
	
	
	@Override
	public Mono<Authentication> authenticate(Authentication authentication) {
		String mail = String.valueOf(authentication.getPrincipal());
		String pw = String.valueOf(authentication.getCredentials());
		return ruds.findByUsername(mail)
				.switchIfEmpty(Mono.error(new UsernameNotFoundException("UsernameNotFound")))
				.flatMap(dtl -> {
					if(!pe.matches(pw, dtl.getPassword())) {
						throw new BadCredentialsException("BadCredential");
					}
					UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(mail, pw, dtl.getAuthorities());
					result.setDetails(dtl);
					return Mono.just(result);
				});
	}

}
