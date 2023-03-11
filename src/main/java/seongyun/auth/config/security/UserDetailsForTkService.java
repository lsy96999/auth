package seongyun.auth.config.security;

import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import seongyun.auth.domain.entity.TkAdmin;
import seongyun.auth.repository.TkAdminRepository;
import seongyun.auth.repository._TkAdminRepository;
import seongyun.auth.service.TkAdminService;

@Service
@RequiredArgsConstructor
public class UserDetailsForTkService implements ReactiveUserDetailsService{
	private final TkAdminService adminService;
	@Override
	public Mono<UserDetails> findByUsername(String username) {
		Mono<TkAdmin> tkadmin = adminService.getTkAdminByMail(username);
		Mono<UserDetails> uds = tkadmin.map(a -> {
			return (UserDetails) a;
		});
		return uds;
	}
}
