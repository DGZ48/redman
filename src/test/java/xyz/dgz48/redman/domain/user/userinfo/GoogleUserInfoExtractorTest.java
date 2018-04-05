package xyz.dgz48.redman.domain.user.userinfo;

import static org.assertj.core.api.Assertions.assertThat;

<<<<<<< HEAD
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
=======
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.dgz48.redman.domain.user.IdpType;
import xyz.dgz48.redman.domain.user.userinfo.UserInfoExtractor;
>>>>>>> implements UserInfoExtractor for each idp


/**
 * Test for {@link UserInfoExtractor}.
 */
<<<<<<< HEAD
public class GoogleUserInfoExtractorTest {
=======
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserInfoExtractorTest {
>>>>>>> implements UserInfoExtractor for each idp

	/**
	 * userInfo.
	 */
	private final Map<String, Object> userInfo = new HashMap<>();

	/**
	 * Init.
	 */
	@Before
	public void init() {
<<<<<<< HEAD
		userInfo.put("sub", "testsub");
=======
>>>>>>> implements UserInfoExtractor for each idp
		userInfo.put("email", "test@example.com");
		userInfo.put("picture", "http://example.com/user.jpg");
	}

	/**
	 * Extract email by Google.
	 */
	@Test
	public void extractEmailByGoogle() {
		// set up
<<<<<<< HEAD
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

=======
		UserInfoExtractor sut = new UserInfoExtractor(IdpType.GOOGLE);

		// exercise
		String actual = sut.getEmail(userInfo);

		// verify
		assertThat(actual).isEqualTo("test@example.com");
	}

	/**
	 * Extract email by GitHub.
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void extractEmailByGitHub() {
		// set up
		UserInfoExtractor sut = new UserInfoExtractor(IdpType.GITHUB);

		// exercise
		String actual = sut.getEmail(userInfo);

		// verify
		assertThat(actual).isNull();
	}

	/**
	 * Extract by Other.
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void extractEmailByOther() {
		// set up
		UserInfoExtractor sut = new UserInfoExtractor(null);

		// exercise
		String actual = sut.getEmail(userInfo);

		// verify
		assertThat(actual).isNull();
	}
>>>>>>> implements UserInfoExtractor for each idp

	/**
	 * Extract picture url by Google.
	 */
	@Test
	public void extractPictureUrlByGoogle() {
		// set up
<<<<<<< HEAD
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

=======
		UserInfoExtractor sut = new UserInfoExtractor(IdpType.GOOGLE);

		// exercise
		String actual = sut.getPictureUrl(userInfo);

		// verify
		assertThat(actual).isEqualTo("http://example.com/user.jpg");
	}

	/**
	 * Extract picture url by GitHub.
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void extractPictureUrlByGitHub() {
		// set up
		UserInfoExtractor sut = new UserInfoExtractor(IdpType.GITHUB);

		// exercise
		String actual = sut.getPictureUrl(userInfo);

		// verify
		assertThat(actual).isNull();
	}

	/**
	 * Extract picture url by Other.
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void extractPictureUrlByOther() {
		// set up
		UserInfoExtractor sut = new UserInfoExtractor(null);

		// exercise
		String actual = sut.getPictureUrl(userInfo);

		// verify
		assertThat(actual).isNull();
	}
>>>>>>> implements UserInfoExtractor for each idp
}
