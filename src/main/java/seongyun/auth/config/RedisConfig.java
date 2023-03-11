package seongyun.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
public class RedisConfig {
	@Bean
	public ReactiveRedisConnectionFactory reactiveRedisConnectionFactory() {
		return new LettuceConnectionFactory("192.168.25.221", 16379);
	}
}
