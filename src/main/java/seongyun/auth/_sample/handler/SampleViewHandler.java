package seongyun.auth._sample.handler;

import org.springframework.http.MediaType;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import seongyun.auth._sample.service.SampleService;
import seongyun.auth.domain.entity.TkAdmin;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
@RequiredArgsConstructor
public class SampleViewHandler {
	private final SampleService sampleService;
	
	public Mono<ServerResponse> sampleView(ServerRequest req){
		ModelMap mm = new ModelMap();
		mm.addAttribute("samples",sampleService.getSamples());
		
		return ReactiveSecurityContextHolder.getContext().flatMap(prin -> {
			TkAdmin adm = (TkAdmin) prin.getAuthentication().getDetails();
			mm.addAttribute("si",adm);
			mm.addAttribute("si2",prin);
			return ok()
					.contentType(MediaType.TEXT_HTML)
					.render("view/sample/index", mm);
		});
	}
}
