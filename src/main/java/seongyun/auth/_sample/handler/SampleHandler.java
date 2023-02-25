package seongyun.auth._sample.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import seongyun.auth._sample.domain.entity.Sample;
import seongyun.auth._sample.service.SampleService;

@Component
@RequiredArgsConstructor
public class SampleHandler {
	private final SampleService sampleService;
	
	public Mono<ServerResponse> allSamples(ServerRequest req){
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(sampleService.getAllSamples(), Sample.class);
	}
}
