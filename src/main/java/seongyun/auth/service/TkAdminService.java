package seongyun.auth.service;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import seongyun.auth.domain.dto.TkAdminJoinDto;
import seongyun.auth.domain.entity.TkAdmin;
import seongyun.auth.repository.TkAdminRepository;

@Service
@RequiredArgsConstructor
public class TkAdminService {
	private final TkAdminRepository tkAdminRepository;
	private final PasswordEncoder pe;
	
	public Mono<TkAdmin> getTkAdminBySn(Long adminSn){
		return tkAdminRepository.getTkAdminBySn(adminSn);
	}
	
	public Mono<TkAdmin> getTkAdminByMail(String mail){
		return tkAdminRepository.getTkAdminByMail(mail);
	}
	
	public Mono<Integer> insertTkAdmin(TkAdminJoinDto dto){
		TkAdmin.builder()
			.adminMail(dto.getAdminMail())
			.adminNm(dto.getAdminNm())
			.adminPw(pe.encode(dto.getAdminPw()));
		return null;
	}
}
