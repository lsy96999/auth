package seongyun.auth._sample.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import lombok.RequiredArgsConstructor;
import seongyun.auth._sample.config.SampleHandlerFilterFunction;
import seongyun.auth._sample.handler.SampleApiHandler;
import seongyun.auth._sample.handler.SampleViewHandler;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;

@Configuration
@RequiredArgsConstructor
public class SampleRouter {
	private final SampleApiHandler sampleApiHandler;
	private final SampleViewHandler sampleViewHandler;
	private final SampleHandlerFilterFunction sampleFilter;
	private static final String EMPTY = "";

	@Bean
	public RouterFunction<ServerResponse> sampleApi(){
		return nest(path("/api/sample"), 
								route(GET(EMPTY),		sampleApiHandler::getSamples)
							.andRoute(GET("/{sn}"),		sampleApiHandler::getSample)
							.andRoute(POST(EMPTY),		sampleApiHandler::addSample)
							.andRoute(PUT(EMPTY),		sampleApiHandler::updateSample)
							.andRoute(DELETE("/{sn}"),	sampleApiHandler::deleteSample))
				.filter(sampleFilter);
	}
	
	@Bean 
	public RouterFunction<ServerResponse> sampleView(){
		return nest(path("/sample"), 
								route(GET(EMPTY),		sampleViewHandler::sampleView));
	}
}
