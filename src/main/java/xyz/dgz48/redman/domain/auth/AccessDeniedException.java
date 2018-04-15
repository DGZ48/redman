package xyz.dgz48.redman.domain.auth;

/**
 * オーナーが許可されていない他人の値を操作した際にスローされる例外.
 */
public class AccessDeniedException extends RuntimeException {

	/**
	 * コンストラクタ.
	 * @param message message
	 */
	public AccessDeniedException(final String message) {
		super(message);
	}

}
