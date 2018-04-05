package xyz.dgz48.redman.domain.user.userinfo;

<<<<<<< HEAD
import java.util.Arrays;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
=======
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.ClientHttpRequestInterceptor;
>>>>>>> implements UserInfoExtractor for each idp
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
<<<<<<< HEAD
=======
import xyz.dgz48.redman.domain.user.IdpType;

import java.util.Arrays;
>>>>>>> implements UserInfoExtractor for each idp

/**
 * GitHubから取得できるユーザ情報を正規化するクラス.
 */
@Component
@Slf4j
<<<<<<< HEAD
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
=======
public class GitHubUserInfoExtractor implements UserInfoExtractor{

	@Autowired
	private OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

	/**
	 * {@link IdpType}に合わせた方法でメールアドレスを抽出する.
	 *
	 * @param oAuth2AuthenticationToken token
	 * @return メールアドレス
	 */
	public String getEmail(final OAuth2AuthenticationToken oAuth2AuthenticationToken) {

		OAuth2AuthorizedClient client =
				oAuth2AuthorizedClientService.loadAuthorizedClient(
						oAuth2AuthenticationToken.getAuthorizedClientRegistrationId(),
						oAuth2AuthenticationToken.getName());

		String accessToken = client.getAccessToken().getTokenValue();
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getInterceptors()
				.add(getBearerTokenInterceptor(accessToken));

		GitHubEmail[] forObject = restTemplate.getForObject("https://api.github.com/user/emails", GitHubEmail[].class);
		return Arrays.stream(forObject).filter(o -> o.isPrimary()).findFirst().get().getEmail();
>>>>>>> implements UserInfoExtractor for each idp
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

<<<<<<< HEAD
	/**
	 * ユーザの画像URLを抽出する.
	 *
	 * @param authenticationToken token
	 * @return 画像のURL
	 */
	public String getPictureUrl(final OAuth2AuthenticationToken authenticationToken) {
		return String.valueOf(authenticationToken.getPrincipal().getAttributes().get("avatar_url"));
=======
	private ClientHttpRequestInterceptor  getBearerTokenInterceptor(String accessToken) {
		ClientHttpRequestInterceptor interceptor =
				(request, bytes, execution) -> {
					request.getHeaders().add("Authorization", "Bearer " + accessToken);
					return execution.execute(request, bytes);
				};
		return interceptor;
	}

	/**
	 * {@link IdpType}に合わせた方法でユーザの画像URLを抽出する.
	 *
	 * @param oAuth2AuthenticationToken token
	 * @return 画像のURL
	 */
	public String getPictureUrl(final OAuth2AuthenticationToken oAuth2AuthenticationToken) {
		return String.valueOf(oAuth2AuthenticationToken.getPrincipal().getAttributes().get("avatar_url"));
>>>>>>> implements UserInfoExtractor for each idp
	}

}
