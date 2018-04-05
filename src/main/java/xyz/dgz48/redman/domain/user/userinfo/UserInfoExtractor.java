package xyz.dgz48.redman.domain.user.userinfo;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

/**
 * 各Idpから取得できるユーザ情報を正規化するクラス.
 */
public interface UserInfoExtractor {

	/**
	 * メールアドレスを抽出する.
	 * @param authenticationToken token
	 * @return メールアドレス
	 */
	String getEmail(final OAuth2AuthenticationToken authenticationToken);

	/**
	 * ユーザの画像URLを抽出する.
	 * @param authenticationToken token
	 * @return 画像のURL
	 */
	String getPictureUrl(final OAuth2AuthenticationToken authenticationToken);

}
