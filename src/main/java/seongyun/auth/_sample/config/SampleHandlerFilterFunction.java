package seongyun.auth._sample.config;

import org.springframework.web.reactive.function.server.ServerResponse;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.HandlerFilterFunction;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;

@Slf4j
@Component
public class SampleHandlerFilterFunction implements HandlerFilterFunction<ServerResponse, ServerResponse>{

	@Override
	public Mono<ServerResponse> filter(ServerRequest request, HandlerFunction<ServerResponse> next) {
		log.debug("here is filter");
		return next.handle(request);
	}

}
