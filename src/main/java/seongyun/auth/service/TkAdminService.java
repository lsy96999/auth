package seongyun.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import seongyun.auth.config.exception.CustomException;
import seongyun.auth.domain.dto.TkAdminJoinDto;
import seongyun.auth.domain.entity.CommonCode;
import seongyun.auth.domain.entity.TkAdmin;
import seongyun.auth.domain.entity.TkAdminRole;
import seongyun.auth.repository.TkAdminRepository;
import seongyun.auth.repository.TkAdminRoleRepository;

@Service
@RequiredArgsConstructor
public class TkAdminService {
	private final TkAdminRepository tkAdminRepository;
	private final TkAdminRoleRepository tkAdminRoleRepository;
	private final PasswordEncoder pe;
	
	@Transactional
	public Mono<TkAdmin> getTkAdminByMail(String mail){
		return tkAdminRepository.findByAdminMail(mail).flatMap(a->{
			return tkAdminRoleRepository.findByAdminSn(a.getAdminSn()).collectList().flatMap(b->{
				a.setRoles(b);
				return Mono.just(a);
			});
		});
	}
	
	public Mono<TkAdmin> getTkAdminBySn(Long adminSn){
		return tkAdminRepository.findById(adminSn);
	}
	
	@Transactional
	public Mono<TkAdmin> insertTkAdmin(TkAdminJoinDto dto){
		
		
		return tkAdminRepository.save(TkAdmin.builder()
				.adminMail(dto.getAdminMail())
				.adminPw(pe.encode(dto.getAdminPw()))
				.adminNm(dto.getAdminNm())
				.tkAdminSttusCode("LIVE")
				.useYn("Y")
				.build()).flatMap(adm -> tkAdminRoleRepository.save(TkAdminRole.builder()
						.useYn("Y")
						.adminSn(adm.getAdminSn())
						.tkAdminRoleCode("ROLE_NORMAL")
						.build()).flatMap(r-> {
							adm.setTkAdminRole(r.getAdminRoleSn());
							return tkAdminRepository.save(adm);
						}));
	}
//	
//	public Mono<TkAdmin> getTkAdminByMail(String mail){
//		return tkAdminRepository.getTkAdminByMail(mail);
//	}
//	
//	@Transactional
//	public Mono<Integer> insertTkAdmin(TkAdminJoinDto dto){
//		return tkAdminRepository.countTkAdminOfMail(dto.getAdminMail())
//				.filter(cnt -> cnt == 0)
//				.switchIfEmpty(Mono.error(new CustomException("중복 회원 입니다.")))
//				.then(tkAdminRepository.nextTkAdminSeq())
//				.flatMap( seq -> {
//					return tkAdminRepository.insertTkAdmin(TkAdmin.builder()
//																	.adminSn(seq)
//																	.adminMail(dto.getAdminMail())
//																	.adminNm(dto.getAdminNm())
//																	.tkAdminSttusCode(CommonCode.builder().codeId("TK_ADMIN_STTUS_CODE").codeValue("LIVE").build())
//																	.adminPw(pe.encode(dto.getAdminPw()))
//																	.useYn("Y")
//																	.createBy(1)
//																	.updateBy(1)
//																	.build())
//						.then(tkAdminRepository.insertTkAdminRole(TkAdminRole.builder()
//																	.adminSn(seq)
//																	.adminRoleCode(CommonCode.builder().codeValue("ROLE_NORMAL").codeId("TK_ADMIN_ROLE_CODE").build())
//																	.createBy(1)
//																	.updateBy(1)
//																	.useYn("Y")
//																	.build()));
//				});
//	}
}
