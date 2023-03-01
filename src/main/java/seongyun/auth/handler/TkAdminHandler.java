package seongyun.auth.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;
@Component
public class TkAdminHandler {
	
	public Mono<ServerResponse> joinPage(ServerRequest req){
		return ok().render("view/join/join");
	}

}
