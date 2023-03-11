package seongyun.auth.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import lombok.RequiredArgsConstructor;
import seongyun.auth.handler.api.ApiCommonCodeHandler;
import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
@Configuration
@RequiredArgsConstructor
public class CommonCodeRouter {
	private final ApiCommonCodeHandler apiCommonCodeHandler;
	private static final String EMPTY = "";
	
	@Bean
	public RouterFunction<ServerResponse> adminRoute(){
		return nest(path("/api/commoncode"), 
								route(GET(EMPTY), apiCommonCodeHandler::getAllCommonCode)
								.andRoute(GET("/{codeId}"), apiCommonCodeHandler::getCommonCodeByCodeId)
								.andRoute(GET("/{codeId}/{codeValue}"), apiCommonCodeHandler::getCommonCodeByCodeIdAndCodeValue)
								.andRoute(POST(EMPTY), apiCommonCodeHandler::insertCommonCode)
								.andRoute(PUT(EMPTY), apiCommonCodeHandler::updateCommonCode)
								.andRoute(DELETE("/{codeSn}"), apiCommonCodeHandler::deleteCommonCode)
							);
	}
}
