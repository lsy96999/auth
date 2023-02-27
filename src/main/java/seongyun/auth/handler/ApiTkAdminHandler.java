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

	public Mono<ServerResponse> test(ServerRequest req){
		return tkAdminService.tests().collectList().flatMap(ab->ok().body(fromValue(ab)));
	}
}
