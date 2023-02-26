package seongyun.auth._sample.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import lombok.RequiredArgsConstructor;
import seongyun.auth._sample.config.SampleHandlerFilterFunction;
import seongyun.auth._sample.handler.SampleHandler;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;

@Configuration
@RequiredArgsConstructor
public class SampleRouter {
	private final SampleHandler sampleHandler;
	private final SampleHandlerFilterFunction sampleFilter;

	@Bean
	public RouterFunction<ServerResponse> sample(){
		return nest(path("/sample"),
					route(GET(""), sampleHandler::allSamples)
					.andNest(path("/view"), route(GET(""), sampleHandler::view1)))
				.filter(sampleFilter);
	}
}
