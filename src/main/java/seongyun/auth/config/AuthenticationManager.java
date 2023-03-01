package seongyun.auth.config;

import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import seongyun.auth.service.UserDetailsForTkService;

@Component
@RequiredArgsConstructor
public class AuthenticationManager implements ReactiveAuthenticationManager{
	private final UserDetailsForTkService udfts;
	@Override
	public Mono<Authentication> authenticate(Authentication authentication) {
		//TODO pw check
		String mail = String.valueOf(authentication.getPrincipal());
		String pw = String.valueOf(authentication.getCredentials());
		return udfts.findByUsername(mail).flatMap(a -> {
			UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(mail, pw, a.getAuthorities());
			result.setDetails(a);
			return Mono.just(result);
		});
	}

}
