package seongyun.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

	@Bean
	public RouterFunction<ServerResponse> sample(){
		return RouterFunctions.route(RequestPredicates.GET("/sample"), (r)->{
			log.debug("hi");
			return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue("sample"));
		});
	}
}
