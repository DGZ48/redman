package xyz.dgz48.redman.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
<<<<<<< HEAD
<<<<<<< HEAD
 * Configuration for {@link RestTemplate}.
=======
 * Configuration for {@link RestTemplate}
>>>>>>> test for GitHub
=======
 * Configuration for {@link RestTemplate}.
>>>>>>> checkstyle
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
