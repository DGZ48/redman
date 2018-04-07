package xyz.dgz48.redman.domain.user.userinfo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Component;
<<<<<<< HEAD
<<<<<<< HEAD
=======
import xyz.dgz48.redman.domain.user.IdpType;

import java.util.Arrays;
>>>>>>> implements UserInfoExtractor for each idp
=======
>>>>>>> checkstyle

/**
 * 各Idpから取得できるユーザ情報を正規化するクラス.
 */
@Component
@Slf4j
public class GoogleUserInfoExtractor implements UserInfoExtractor {

	/**
<<<<<<< HEAD
<<<<<<< HEAD
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
=======
	 * {@link IdpType}に合わせた方法でメールアドレスを抽出する.
	 * @param oAuth2AuthenticationToken token
=======
	 * メールアドレスを抽出する.
	 * @param authenticationToken token
>>>>>>> checkstyle
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
<<<<<<< HEAD
	public String getPictureUrl(final OAuth2AuthenticationToken oAuth2AuthenticationToken) {
		return String.valueOf(oAuth2AuthenticationToken.getPrincipal().getAttributes().get("picture"));
>>>>>>> implements UserInfoExtractor for each idp
=======
	public String getPictureUrl(final OAuth2AuthenticationToken authenticationToken) {
		return String.valueOf(authenticationToken.getPrincipal().getAttributes().get("picture"));
>>>>>>> checkstyle
	}

}
