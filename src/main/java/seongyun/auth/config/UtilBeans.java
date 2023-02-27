package seongyun.auth.config;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.NameTokenizers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilBeans {
	
	@Bean
	public ModelMapper mm() {
		ModelMapper mm = new ModelMapper();
		mm.getConfiguration()
			.setDestinationNameTokenizer(NameTokenizers.UNDERSCORE)
			.setSourceNameTokenizer(NameTokenizers.UNDERSCORE);
		mm.getTypeMap(String.class, LocalDateTime.class);
		return mm;
	}

}
