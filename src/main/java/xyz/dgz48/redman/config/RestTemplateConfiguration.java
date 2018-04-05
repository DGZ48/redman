package xyz.dgz48.redman.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Configuration for {@link RestTemplate}
 */
@Configuration
public class RestTemplateConfiguration {

	/**
	 * Handle by container because of using mock for test.
	 * @return restTemplate
	 */
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
