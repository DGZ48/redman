package xyz.dgz48.redman.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Configration about security
 *
 * @since
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .logout().permitAll()
                .and()
                .oauth2Login()
                .loginPage("/login").permitAll();
    }
}
