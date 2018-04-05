package xyz.dgz48.redman.domain.user.userinfo;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;


/**
 * Test for {@link UserInfoExtractor}.
 */
public class GoogleUserInfoExtractorTest {

	/**
	 * userInfo.
	 */
	private final Map<String, Object> userInfo = new HashMap<>();

	/**
	 * Init.
	 */
	@Before
	public void init() {
		userInfo.put("sub", "testsub");
		userInfo.put("email", "test@example.com");
		userInfo.put("picture", "http://example.com/user.jpg");
	}

	/**
	 * Extract email by Google.
	 */
	@Test
	public void extractEmailByGoogle() {
		// set up
		UserInfoExtractor sut = new GoogleUserInfoExtractor();

		Set<GrantedAuthority> authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

		OAuth2User oidcUser = new DefaultOidcUser(authorities, new OidcIdToken("sampletoken", Instant.MIN, Instant.MAX, userInfo));
		OAuth2AuthenticationToken token = new OAuth2AuthenticationToken(oidcUser, authorities, "google");

		// exercise
		String actual = sut.getEmail(token);

		// verify
		assertThat(actual).isEqualTo("test@example.com");
	}


	/**
	 * Extract picture url by Google.
	 */
	@Test
	public void extractPictureUrlByGoogle() {
		// set up
		UserInfoExtractor sut = new GoogleUserInfoExtractor();

		Set<GrantedAuthority> authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

		OAuth2User oidcUser = new DefaultOidcUser(authorities, new OidcIdToken("sampletoken", Instant.MIN, Instant.MAX, userInfo));
		OAuth2AuthenticationToken token = new OAuth2AuthenticationToken(oidcUser, authorities, "google");

		// exercise
		String actual = sut.getPictureUrl(token);

		// verify
		assertThat(actual).isEqualTo("http://example.com/user.jpg");
	}

}
