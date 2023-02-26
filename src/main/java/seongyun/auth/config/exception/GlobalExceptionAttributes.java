package seongyun.auth.config.exception;

import java.util.Map;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.ServerRequest;

@Configuration
public class GlobalExceptionAttributes extends DefaultErrorAttributes{
	@Override
	public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
		Map<String, Object> map = super.getErrorAttributes(request, options);
		Throwable throwable = getError(request);
		map.put("description", throwable.getMessage());
		if(throwable instanceof CustomException) {
//			CustomException ce = (CustomException) throwable;
		}
		return map;
	}
}
