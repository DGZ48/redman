package xyz.dgz48.redman.domain.user.userinfo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Component;

/**
 * 各Idpから取得できるユーザ情報を正規化するクラス.
 */
@Component
@Slf4j
public class GoogleUserInfoExtractor implements UserInfoExtractor {

	/**
	 * メールアドレスを抽出する.
	 * @param authenticationToken token
	 * @return メールアドレス
	 */
	public String getEmail(final OAuth2AuthenticationToken authenticationToken) {
		return String.valueOf(authenticationToken.getPrincipal().getAttributes().get("email"));
	}

	/**
	 * ユーザの画像URLを抽出する.
	 * @param authenticationToken token
	 * @return 画像のURL
	 */
	public String getPictureUrl(final OAuth2AuthenticationToken authenticationToken) {
		return String.valueOf(authenticationToken.getPrincipal().getAttributes().get("picture"));
	}

}
