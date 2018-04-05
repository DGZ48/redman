package xyz.dgz48.redman.domain.user.userinfo;

import java.util.Arrays;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * GitHubから取得できるユーザ情報を正規化するクラス.
 */
@Component
@Slf4j
public class GitHubUserInfoExtractor implements UserInfoExtractor {

	/**
	 * OAuth2AuthorizedClientService.
	 */
	@Autowired
	private OAuth2AuthorizedClientService authorizedClientService;

	/**
	 * RestTemplate.
	 */
	@Autowired
	private RestTemplate restTemplate;

	/**
	 * メールアドレスを抽出する.
	 *
	 * @param authenticationToken token
	 * @return メールアドレス
	 */
	public String getEmail(final OAuth2AuthenticationToken authenticationToken) {

		log.debug("ClientRegistrationId:{}", authenticationToken.getAuthorizedClientRegistrationId());
		log.debug("Name:{}", authenticationToken.getName());

		OAuth2AuthorizedClient client =
				authorizedClientService.loadAuthorizedClient(
						authenticationToken.getAuthorizedClientRegistrationId(),
						authenticationToken.getName());

		String accessToken = client.getAccessToken().getTokenValue();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + accessToken);

		HttpEntity<String> entity = new HttpEntity<>(headers);
		ResponseEntity<GitHubEmail[]> forObject = restTemplate.exchange("https://api.github.com/user/emails", HttpMethod.GET, entity, GitHubEmail[].class);

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
	 * @param authenticationToken token
	 * @return 画像のURL
	 */
	public String getPictureUrl(final OAuth2AuthenticationToken authenticationToken) {
		return String.valueOf(authenticationToken.getPrincipal().getAttributes().get("avatar_url"));
	}

}
