package seongyun.auth._sample.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import seongyun.auth._sample.domain.entity.Sample;
import seongyun.auth._sample.service.SampleService;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;
//import static org.springframework.web.reactive.function.server.ServerResponse.notFound;

@Component
@RequiredArgsConstructor
public class SampleApiHandler {
	private final SampleService sampleService;
	
	public Mono<ServerResponse> getSamples(ServerRequest req){
		return ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(sampleService.getSamples(), Sample.class);
	}
	
	public Mono<ServerResponse> getSample(ServerRequest req){
		Long sn = Long.parseLong(req.pathVariable("sn"));
		return ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(sampleService.getSample(sn), Sample.class);
	}
	
	public Mono<ServerResponse> addSample(ServerRequest req){
		Mono<Sample> sample = req.bodyToMono(Sample.class);
		return sample.flatMap(s -> {
			return ok()
					.contentType(MediaType.APPLICATION_JSON)
					.body(sampleService.saveSample(s), Sample.class);
		});
	}
	
	public Mono<ServerResponse> updateSample(ServerRequest req){
		Mono<Sample> sample = req.bodyToMono(Sample.class);
		return sample.flatMap(s -> {
			return ok()
					.contentType(MediaType.APPLICATION_JSON)
					.body(sampleService.saveSample(s), Sample.class);
		});
	}
	
	public Mono<ServerResponse> deleteSample(ServerRequest req){
		Long sn = Long.parseLong(req.pathVariable("sn"));
		return ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(sampleService.deleteSample(sn), Sample.class);
			
	}
}
