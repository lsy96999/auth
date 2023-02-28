package seongyun.auth.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import seongyun.auth.domain.entity.TkAdmin;
import seongyun.auth.repository.TkAdminRepository;

@Service
@RequiredArgsConstructor
public class TkAdminService {
	private final TkAdminRepository tkAdminRepository;
	
	public Mono<TkAdmin> getTkAdminBySn(Long adminSn){
		return tkAdminRepository.getTkAdminBySn(adminSn);
	}
}
