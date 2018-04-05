package xyz.dgz48.redman.domain.user.userinfo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.time.Instant;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;


/**
 * Test for {@link UserInfoExtractor}.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class GItHubUserInfoExtractorTest {

	/**
	 * userInfo.
	 */
	private final Map<String, Object> userInfo = new HashMap<>();

	@Autowired
	GitHubUserInfoExtractor sut;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	OAuth2AuthorizedClientService authorizedClientService;

	/**
	 * Init.
	 */
	@Before
	public void init() {
		userInfo.put("sub", "testsub");
		userInfo.put("avatar_url", "http://example.com/user.jpg");
	}

	/**
	 * Extract email by Google.
	 */
	@Test
	public void extractEmailByGitHub() {
		// set up
		// prepare mock for rest template
		MockRestServiceServer mockServer = MockRestServiceServer.bindTo(restTemplate).build();
		mockServer.expect(requestTo("https://api.github.com/user/emails"))
				.andRespond(withSuccess("[{\"email\":\"test@example.com\", \"verified\":true, \"primary\":true, \"visibility\":\"true\"}]", MediaType.APPLICATION_JSON_UTF8));

		// prepare token
		Set<GrantedAuthority> authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		OAuth2User oidcUser = new DefaultOidcUser(authorities, new OidcIdToken("sampletoken", Instant.MIN, Instant.MAX, userInfo));
		OAuth2AuthenticationToken token = new OAuth2AuthenticationToken(oidcUser, authorities, "github");

		// register authorized client for using token by RestTemplate.
		ClientRegistration registration = CommonOAuth2Provider.GITHUB.getBuilder("github").clientId("id").clientSecret("secret").build();
		OAuth2AuthorizedClient oAuth2AuthorizedClient = new OAuth2AuthorizedClient(registration, "testsub", new OAuth2AccessToken(OAuth2AccessToken.TokenType.BEARER,"sampletoken" ,Instant.MIN, Instant.MAX));

		authorizedClientService.saveAuthorizedClient(oAuth2AuthorizedClient, token);

		// exercise
		String actual = sut.getEmail(token);

		// verify
		assertThat(actual).isEqualTo("test@example.com");
	}


	/**
	 * Extract picture url by Google.
	 */
	@Test
	public void extractPictureUrlByGitHub() {
		// set up
		UserInfoExtractor sut = new GitHubUserInfoExtractor();

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
