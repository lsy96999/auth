package seongyun.auth.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import seongyun.auth.domain.dto.CommonCodeDto;
import seongyun.auth.domain.entity.CommonCode;
import seongyun.auth.repository.CommonCodeRepository;

@Service
@RequiredArgsConstructor
public class CommonCodeService {
	private final CommonCodeRepository codeRepository;
	
	public Flux<CommonCode> getAllCommonCode() {
		return codeRepository.findAll();
	}
	
	public Flux<CommonCode> getCommonCodeByCodeId(String codeId){
		return codeRepository.findByCodeId(codeId);
	}
	
	public Mono<CommonCode> getCommonCodeByCodeIdAndCodeValue(String codeId, String codeValue){
		return codeRepository.findByCodeIdAndCodeValue(codeId, codeValue);
	}
	
	public Mono<CommonCode> saveCommonCode(CommonCodeDto dto) {
		return codeRepository.save(CommonCode.builder()
				.codeId(dto.getCodeId())
				.codeValue(dto.getCodeValue())
				.codeDesc(dto.getCodeDesc())
				.codeNm(dto.getCodeNm())
				.useYn("Y")
				.codeGroup(dto.getCodeGroup())
				.build());
	}
	
	public Mono<CommonCode> updateCommonCode(CommonCodeDto dto) {
		return codeRepository.save(CommonCode.builder()
				.codeSn(dto.getCodeSn())
				.codeId(dto.getCodeId())
				.codeValue(dto.getCodeValue())
				.codeDesc(dto.getCodeDesc())
				.codeNm(dto.getCodeNm())
				.codeGroup(dto.getCodeGroup())
				.build());
	}
	
	public Mono<Void> deleteCommonCode(Long codeSn) {
		return codeRepository.deleteById(codeSn);
	}
}
