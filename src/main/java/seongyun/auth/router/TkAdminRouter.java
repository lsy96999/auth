package seongyun.auth.router;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import lombok.RequiredArgsConstructor;
import seongyun.auth.handler.TkAdminHandler;
import seongyun.auth.handler.api.ApiTkAdminHandler;

@Configuration
@RequiredArgsConstructor
public class TkAdminRouter {
	private final TkAdminHandler tkAdminHandler;
	private final ApiTkAdminHandler apiTkAdminHandler;
	private static final String EMPTY = "";

	@Bean
	public RouterFunction<ServerResponse> adminRoute(){
		return nest(path("/api/tkadmin"), 
								route(GET("/{adminSn}"),		apiTkAdminHandler::getTkAdminBySn)
								.andRoute(POST(EMPTY),			apiTkAdminHandler::insertTkAdmin)
							);
	}
	
	@Bean
	public RouterFunction<ServerResponse> adminViewRoute(){
		return nest(path("/join"), 
								route(GET(EMPTY),		tkAdminHandler::joinPage)
							);
	}
}
