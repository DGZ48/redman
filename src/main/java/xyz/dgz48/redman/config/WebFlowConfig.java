package xyz.dgz48.redman.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import xyz.dgz48.redman.web.flow.WebFlowDataStoreImpl;

/**
 * Spring configuration for WebFlow.
 *
 * @author win2cot
 */
@Configuration
public class WebFlowConfig {

	/**
	 * DataStore兼HandlerInterceptorを返す.
	 *
	 * @return DataStore兼HandlerInterceptor
	 */
	@Bean
	public WebFlowDataStoreImpl handlerInterceptor() {
		return new WebFlowDataStoreImpl();
	}

	/**
	 * WebMvcConfigurerAdapter.
	 *
	 * @return WebMvcConfigurerAdapter
	 */
	@Bean
    public WebMvcConfigurer webMvcConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addInterceptors(final InterceptorRegistry registry) {
				// とりあえず全体にかけてみる
				registry.addInterceptor(handlerInterceptor()).addPathPatterns("/**");
			}
		};
	}
}
