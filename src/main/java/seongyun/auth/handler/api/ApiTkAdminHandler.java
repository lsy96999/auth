package seongyun.auth.handler.api;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import seongyun.auth.config.RVO;
import seongyun.auth.domain.dto.TkAdminJoinDto;
import seongyun.auth.service.TkAdminService;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
@RequiredArgsConstructor
public class ApiTkAdminHandler {
	private final TkAdminService tkAdminService;
	
//	public Mono<ServerResponse> getTkAdminBySn (ServerRequest req) {
//		Long adminSn = Long.parseLong(req.pathVariable("adminSn"));
//		return tkAdminService.getTkAdminBySn(adminSn).flatMap(adm->{
//			return ok().body(fromValue(RVO.builder()
//											.code("0000")
//											.message("조회됐습니다.")
//											.data(adm)
//										.build()));
//		})
//		.switchIfEmpty(ok().body(fromValue(RVO.builder()
//											.code("9999")
//											.message("존재하지 않습니다.")
//										.build())));
//	}
	
//	public Mono<ServerResponse> insertTkAdmin(ServerRequest req){
//		Mono<TkAdminJoinDto> rdto = req.bodyToMono(TkAdminJoinDto.class);
//		return rdto.flatMap(dto -> {
//			return tkAdminService.insertTkAdmin(dto).flatMap( seq -> {
//				return ok().body(fromValue(RVO.builder()
//												.code("0000")
//												.message("등록됐습니다.")
//												.data(seq)
//											.build()));
//			});
//		});
//	}
}
