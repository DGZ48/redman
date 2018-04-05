package xyz.dgz48.redman.domain.user.userinfo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Component;
import xyz.dgz48.redman.domain.user.IdpType;

import java.util.Arrays;

/**
 * 各Idpから取得できるユーザ情報を正規化するクラス.
 */
@Component
@Slf4j
public class GoogleUserInfoExtractor implements UserInfoExtractor {

	/**
	 * {@link IdpType}に合わせた方法でメールアドレスを抽出する.
	 * @param oAuth2AuthenticationToken token
	 * @return メールアドレス
	 */
	public String getEmail(final OAuth2AuthenticationToken oAuth2AuthenticationToken) {
		return String.valueOf(oAuth2AuthenticationToken.getPrincipal().getAttributes().get("email"));
	}

	/**
	 * {@link IdpType}に合わせた方法でユーザの画像URLを抽出する.
	 * @param oAuth2AuthenticationToken token
	 * @return 画像のURL
	 */
	public String getPictureUrl(final OAuth2AuthenticationToken oAuth2AuthenticationToken) {
		return String.valueOf(oAuth2AuthenticationToken.getPrincipal().getAttributes().get("picture"));
	}

}
