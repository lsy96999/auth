package seongyun.auth._sample.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import seongyun.auth._sample.service.SampleService;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
@RequiredArgsConstructor
public class SampleViewHandler {
	private final SampleService sampleService;
	
	public Mono<ServerResponse> sampleView(ServerRequest re){
		ModelMap mm = new ModelMap();
		mm.addAttribute("samples",sampleService.getSamples());
		return ok()
				.contentType(MediaType.TEXT_HTML)
				.render("view/sample/index", mm);
	}
}
