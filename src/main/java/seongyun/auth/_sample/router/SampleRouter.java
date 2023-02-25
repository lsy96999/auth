package seongyun.auth._sample.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import lombok.RequiredArgsConstructor;
import seongyun.auth._sample.handler.SampleHandler;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
@RequiredArgsConstructor
public class SampleRouter {
	private final SampleHandler sampleHandler;

	@Bean
	public RouterFunction<ServerResponse> sample(){
		return RouterFunctions.route(GET("/sample"), sampleHandler::allSamples);
	}
}
