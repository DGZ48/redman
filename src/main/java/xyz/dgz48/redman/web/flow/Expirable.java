package xyz.dgz48.redman.web.flow;

import java.time.LocalDateTime;

/**
 * 有効期限をもつことを示す.
 *
 * @param <T> 管理対象クラス
 * @author win2cot
 */
interface Expirable<T> {

	/**
	 * 有効期限を返す.
	 *
	 * @return 有効期限
	 */
	LocalDateTime getExpirationDateTime();

	/**
	 * 有効期限を設定する.
	 *
	 * @param expirationDateTime 有効期限
	 */
	void setExpirationDateTime(LocalDateTime expirationDateTime);

	/**
	 * 管理対象オブジェクトを返す.
	 *
	 * @return 管理対象オブジェクト
	 */
	T getTarget();

	/**
	 * 有効期限切れかを返す.
	 *
	 * @return 有効期限切れか
	 */
	boolean isExpired();

}
