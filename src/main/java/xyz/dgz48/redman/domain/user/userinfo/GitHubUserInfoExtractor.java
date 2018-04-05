package xyz.dgz48.redman.domain.user.userinfo;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import xyz.dgz48.redman.domain.user.IdpType;

import java.util.Arrays;
import java.util.Collections;

/**
 * GitHubから取得できるユーザ情報を正規化するクラス.
 */
@Component
@Slf4j
public class GitHubUserInfoExtractor implements UserInfoExtractor{

	@Autowired
	private OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

	@Autowired
	RestTemplate restTemplate;

	/**
	 * {@link IdpType}に合わせた方法でメールアドレスを抽出する.
	 *
	 * @param oAuth2AuthenticationToken token
	 * @return メールアドレス
	 */
	public String getEmail(final OAuth2AuthenticationToken oAuth2AuthenticationToken) {

		log.debug("ClientRegistrationId:{}", oAuth2AuthenticationToken.getAuthorizedClientRegistrationId());
		log.debug("Name:{}", oAuth2AuthenticationToken.getName());

		OAuth2AuthorizedClient client =
				oAuth2AuthorizedClientService.loadAuthorizedClient(
						oAuth2AuthenticationToken.getAuthorizedClientRegistrationId(),
						oAuth2AuthenticationToken.getName());

		String accessToken = client.getAccessToken().getTokenValue();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + accessToken);

		HttpEntity<String> entity = new HttpEntity<>(headers);
		ResponseEntity<GitHubEmail[]> forObject = restTemplate.exchange("https://api.github.com/user/emails", HttpMethod.GET, entity, GitHubEmail[].class);

//		GitHubEmail[] forObject = restTemplate.getForObject("https://api.github.com/user/emails", entity, GitHubEmail[].class);
		return Arrays.stream(forObject.getBody()).filter(o -> o.isPrimary()).findFirst().get().getEmail();
	}

	/**
	 * Response for https://api.github.com/user/emails.
	 */
	@Value
	static class GitHubEmail {

		/**
		 * _
		 * email.
		 */
		private String email;

		/**
		 * _
		 * verified.
		 */
		private boolean verified;

		/**
		 * _
		 * primary.
		 */
		private boolean primary;

		/**
		 * _
		 * visibility.
		 */
		private String visibility;

	}

	/**
	 * {@link IdpType}に合わせた方法でユーザの画像URLを抽出する.
	 *
	 * @param oAuth2AuthenticationToken token
	 * @return 画像のURL
	 */
	public String getPictureUrl(final OAuth2AuthenticationToken oAuth2AuthenticationToken) {
		return String.valueOf(oAuth2AuthenticationToken.getPrincipal().getAttributes().get("avatar_url"));
	}

}
