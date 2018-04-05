package xyz.dgz48.redman.domain.user.userinfo;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
<<<<<<< HEAD
<<<<<<< HEAD
=======
import xyz.dgz48.redman.domain.user.IdpType;
>>>>>>> implements UserInfoExtractor for each idp
=======
>>>>>>> checkstyle

/**
 * 各Idpから取得できるユーザ情報を正規化するクラス.
 */
public interface UserInfoExtractor {

	/**
<<<<<<< HEAD
<<<<<<< HEAD
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
=======
	 * {@link IdpType}に合わせた方法でメールアドレスを抽出する.
	 * @param oAuth2AuthenticationToken token
=======
	 * メールアドレスを抽出する.
	 * @param authenticationToken token
>>>>>>> checkstyle
	 * @return メールアドレス
	 */
	String getEmail(final OAuth2AuthenticationToken authenticationToken);

	/**
	 * ユーザの画像URLを抽出する.
	 * @param authenticationToken token
	 * @return 画像のURL
	 */
<<<<<<< HEAD
	public String getPictureUrl(final OAuth2AuthenticationToken oAuth2AuthenticationToken);
>>>>>>> implements UserInfoExtractor for each idp
=======
	String getPictureUrl(final OAuth2AuthenticationToken authenticationToken);
>>>>>>> checkstyle

}
