package seongyun.auth._sample.handler;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import seongyun.auth._sample.domain.entity.Sample;
import seongyun.auth._sample.service.SampleService;
import seongyun.auth.config.RVO;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
@RequiredArgsConstructor
public class SampleApiHandler {
	private final SampleService sampleService;
	
	public Mono<ServerResponse> getSamplesPage(ServerRequest req){
		Integer size = Integer.valueOf(req.queryParam("size").orElse("10"));
		Integer page = Integer.valueOf(req.queryParam("page").orElse("0"));
		Sort srt = Sort.by("sampleNm", "sampleSn").descending();
		Mono<Page<Sample>> fs = sampleService.getSamplesPage(PageRequest.of(page, size, srt));
		return fs.flatMap(s -> {
					return ok()
							.contentType(MediaType.APPLICATION_JSON)
							.body(fromValue(RVO.builder().code("").message("").data(s).build()));
				});
//				.switchIfEmpty(ok().body(fromValue(rvn)));
	}
	
	public Mono<ServerResponse> getSamples(ServerRequest req){
		Flux<Sample> fs = sampleService.getSamples();
		return fs.collectList().flatMap(s->{
					return ok()
							.contentType(MediaType.APPLICATION_JSON)
							.body(fromValue(
										RVO.builder()
												.code("xxx")
												.message("msg")
												.data(s)
											.build()));
				})
				.switchIfEmpty(ok().body(fromValue(RVO.builder().code("code").message("").data(null).build())));
	}
	
	public Mono<ServerResponse> getSample(ServerRequest req){
		Long sn = Long.parseLong(req.pathVariable("sn"));
		Mono<Sample> fs = sampleService.getSample(sn);
		return fs.flatMap(s -> {
					return ok()
							.contentType(MediaType.APPLICATION_JSON)
							.body(fromValue(RVO.builder().code("xxx").message("msg").data(s).build()));
				})
				.switchIfEmpty(ok().body(fromValue(RVO.builder()
														.code("")
														.message("")
														.data(null))));
	}
	
	public Mono<ServerResponse> addSample(ServerRequest req){
		Mono<Sample> sample = req.bodyToMono(Sample.class);
		return sample.flatMap(p -> {
			return sampleService.saveSample(p).flatMap(s -> {
				return ok()
						.contentType(MediaType.APPLICATION_JSON)
						.body(fromValue(RVO.builder().code("code").message("").data(s).build()));
			});
		});
	}
	
	public Mono<ServerResponse> updateSample(ServerRequest req){
		Mono<Sample> sample = req.bodyToMono(Sample.class);
		return sample.flatMap(p -> {
			return sampleService.saveSample(p).flatMap(s->{
				return ok()
						.contentType(MediaType.APPLICATION_JSON)
						.body(fromValue(RVO.builder().code("code").message("").data(s).build()));
			});
		});
	}
	
	public Mono<ServerResponse> deleteSample(ServerRequest req){
		Long sn = Long.parseLong(req.pathVariable("sn"));
		return sampleService.deleteSample(sn).flatMap(s->{
			return ok()
					.contentType(MediaType.APPLICATION_JSON)
					.body(fromValue(RVO.builder().code("code").message("").data(null).build()));
		}).switchIfEmpty(ok().body(fromValue(RVO.builder().code("code").message("").data(null).build())));
	}
}
