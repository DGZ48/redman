package xyz.dgz48.redman.domain.user.userinfo;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import xyz.dgz48.redman.domain.user.IdpType;

/**
 * 各Idpから取得できるユーザ情報を正規化するクラス.
 */
public interface UserInfoExtractor {

	/**
	 * {@link IdpType}に合わせた方法でメールアドレスを抽出する.
	 * @param oAuth2AuthenticationToken token
	 * @return メールアドレス
	 */
	public String getEmail(final OAuth2AuthenticationToken oAuth2AuthenticationToken);

	/**
	 * {@link IdpType}に合わせた方法でユーザの画像URLを抽出する.
	 * @param oAuth2AuthenticationToken token
	 * @return 画像のURL
	 */
	public String getPictureUrl(final OAuth2AuthenticationToken oAuth2AuthenticationToken);

}
