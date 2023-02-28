package seongyun.auth.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import seongyun.auth.service.TkAdminService;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
@RequiredArgsConstructor
public class ApiTkAdminHandler {
	private final TkAdminService tkAdminService;
	
	public Mono<ServerResponse> getTkAdminBySn (ServerRequest req) {
		Long adminSn = Long.parseLong(req.pathVariable("adminSn"));
		return tkAdminService.getTkAdminBySn(adminSn).flatMap(a->{
			return ok().body(fromValue(a));
		});
	}
}
