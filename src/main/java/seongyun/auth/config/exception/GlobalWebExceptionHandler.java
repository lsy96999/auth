package seongyun.auth.config.exception;


import java.util.List;
import java.util.Map;
import org.springframework.boot.autoconfigure.web.WebProperties.Resources;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
//import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.result.view.ViewResolver;
import reactor.core.publisher.Mono;

@Configuration
//@Order(Ordered.HIGHEST_PRECEDENCE) //TODO: 404 catch issue
@Order(-2)
public class GlobalWebExceptionHandler extends AbstractErrorWebExceptionHandler{
	public GlobalWebExceptionHandler(GlobalExceptionAttributes globalErrorAttributes, 
			ApplicationContext applicationContext,
			List<ViewResolver> viewResolverProvider,
			ServerCodecConfigurer serverCodecConfigurer) {
		super(globalErrorAttributes, new Resources(), applicationContext);
		super.setMessageReaders(serverCodecConfigurer.getReaders());
		super.setMessageWriters(serverCodecConfigurer.getWriters());
		this.setViewResolvers(viewResolverProvider);
	}
	
	@Override
	protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
		return RouterFunctions.route(RequestPredicates.all().and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), this::renderErrorResponse);
	}
	
	private Mono<ServerResponse> renderErrorResponse(ServerRequest request) {
		Map<String, Object> errorPropertiesMap = getErrorAttributes(request, ErrorAttributeOptions.defaults());
		Integer status = Integer.parseInt(errorPropertiesMap.get("status").toString());
		if(request.uri().getPath().contains("api")) {
			return ServerResponse.status(status)
					.contentType(MediaType.APPLICATION_JSON)
					.body(BodyInserters.fromValue(errorPropertiesMap));
		} else {
			return ServerResponse.status(status)
					.contentType(MediaType.TEXT_HTML)
					.render("error/error", errorPropertiesMap);
		}
	}
}
