package xyz.dgz48.redman.domain.user;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Idpの種別.
 */
@AllArgsConstructor
public enum IdpType {

	/**
	 * Google.
	 */
	GOOGLE("google"),

	/**
	 * GitHub.(Not Implemented already).
	 */
	GITHUB("github");

	/**
	 * AuthorizedClientRegistrationId in OAuth2AuthenticationToken.
	 */
	@Getter
	private String clientRegistrationId;

	/**
	 * Find by AuthorizedClientRegistrationId in OAuth2AuthenticationToken.
	 * @param clientRegistrationId clientRegistrationId
	 * @return IdpType
	 */
	public static IdpType findByClientRegistrationId(final String clientRegistrationId) {
		return Arrays.stream(values()).filter(v -> v.clientRegistrationId.equals(clientRegistrationId)).findFirst().get();

	}

}
