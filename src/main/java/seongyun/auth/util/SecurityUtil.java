package seongyun.auth.util;

import org.springframework.security.core.context.ReactiveSecurityContextHolder;

import reactor.core.publisher.Mono;
import seongyun.auth.domain.entity.TkAdmin;

public class SecurityUtil {
	
	public static Mono<TkAdmin> getUser() {
		return ReactiveSecurityContextHolder.getContext().flatMap(holder ->{
			return Mono.just((TkAdmin)holder.getAuthentication().getDetails());
		});
	}

}
