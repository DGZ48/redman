package xyz.dgz48.redman.web;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.time.Instant;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * OAuth2 token provider(put to {@SecurityContext} for integration test.
 */
public class WithOAuth2SecurityContextFactory  implements WithSecurityContextFactory<WithMockOAuth2User>{

    /**
     * Create security context with OAuth2 token.
     *
     * @param user user
     * @return security context
     */
    @Override
    public SecurityContext createSecurityContext(WithMockOAuth2User user) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", "testsub");

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        OAuth2User oAuth2User = new DefaultOidcUser(authorities, new OidcIdToken("sampletoken", Instant.MIN, Instant.MAX, claims));
        OAuth2AuthenticationToken token = new OAuth2AuthenticationToken(oAuth2User, authorities, "test-client");
        context.setAuthentication(token);
        return context;
    }
}
