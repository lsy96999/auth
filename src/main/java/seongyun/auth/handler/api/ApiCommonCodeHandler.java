package seongyun.auth.handler.api;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import seongyun.auth.config.RVO;
import seongyun.auth.config.exception.CustomException;
import seongyun.auth.domain.dto.CommonCodeDto;
import seongyun.auth.service.CommonCodeService;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;
@Component
@RequiredArgsConstructor
public class ApiCommonCodeHandler {
	private final CommonCodeService codeService;
	public Mono<ServerResponse> getAllCommonCode (ServerRequest req) {
		return codeService.getAllCommonCode()
							.collectList()
							.flatMap(c->ok()
										.body(fromValue(
											RVO.builder()
												.data(c)
												.build()
											)
									));
	}
	
	public Mono<ServerResponse> getCommonCodeByCodeIdAndCodeValue (ServerRequest req) {
		String codeId = req.pathVariable("codeId");
		String codeValue = req.pathVariable("codeValue");
		return codeService.getCommonCodeByCodeIdAndCodeValue(codeId, codeValue)
				.flatMap(c->ok().body(fromValue(RVO.builder().data(c).build())));
	}
	
	public Mono<ServerResponse> getCommonCodeByCodeId (ServerRequest req) {
		String codeId = req.pathVariable("codeId");
		return codeService.getCommonCodeByCodeId(codeId)
								.collectList()
								.flatMap(c->ok().body(fromValue(
										RVO.builder()
										.data(c)
										.build())));
	}
	
	public Mono<ServerResponse> insertCommonCode (ServerRequest req) {
		Mono<CommonCodeDto> dto = req.bodyToMono(CommonCodeDto.class);
		return dto.flatMap(d->codeService.saveCommonCode(d).flatMap(r->ok().body(fromValue(RVO.builder().data(r).build()))));
	}
	
	public Mono<ServerResponse> updateCommonCode (ServerRequest req) {
		Mono<CommonCodeDto> dto = req.bodyToMono(CommonCodeDto.class);
		return dto.flatMap(d->{
			if(d.getCodeSn() == null) {
				throw new CustomException("NoCodeSn");
			}
			return codeService.updateCommonCode(d).flatMap(r->ok().body(fromValue(RVO.builder().data(r).build())));
		});
	}
	
	public Mono<ServerResponse> deleteCommonCode (ServerRequest req) {
		Long codeSn = Long.parseLong(req.pathVariable("codeSn"));
		return codeService.deleteCommonCode(codeSn).flatMap(r->ok().body(fromValue(RVO.builder().data(r).build())));
	}
}
