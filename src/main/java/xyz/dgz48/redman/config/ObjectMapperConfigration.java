package xyz.dgz48.redman.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;

public class ObjectMapperConfigration {

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}

}
