package seongyun.auth.config.WS;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.server.WebSocketService;
import org.springframework.web.reactive.socket.server.support.HandshakeWebSocketService;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;
import org.springframework.web.reactive.socket.server.upgrade.ReactorNettyRequestUpgradeStrategy;
//https://howtodoinjava.com/spring-webflux/reactive-websockets/
@Configuration
public class WSConfig {

	@Bean
	public WSHandler wsHandler() {
		return new WSHandler();
	}
	
	@Bean
	public HandlerMapping handlerMapping() {
		Map<String, WebSocketHandler> map = new HashMap<>();
		map.put("/echo", wsHandler());
		
		SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
		mapping.setUrlMap(map);
		mapping.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return mapping;
	}
	
	@Bean
	public WebSocketHandlerAdapter handlerAdapter() {
		return new WebSocketHandlerAdapter(webSocketService());
	}
	
	@Bean
	public WebSocketService webSocketService() {
		return new HandshakeWebSocketService(new ReactorNettyRequestUpgradeStrategy());
	}
}
