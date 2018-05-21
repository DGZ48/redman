package xyz.dgz48.redman.web.flow;

import java.time.LocalDateTime;

/**
 * 有効期限をもつオブジェクトの管理クラス.
 *
 * @param <T> 管理対象クラス
 * @author win2cot
 */
class AbstractExpirable<T> implements Expirable<T> {

	/**
	 * 有効期限.
	 */
	private LocalDateTime expirationDateTime;

	/**
	 * 管理対象.
	 */
	private T target;

	/**
	 * コンストラクタ.
	 *
	 * @param target 管理対象
	 * @param expirationDateTime 有効期限
	 */
	AbstractExpirable(final T target, final LocalDateTime expirationDateTime) {
		this.target = target;
		this.expirationDateTime = expirationDateTime;
	}

	@Override
	public LocalDateTime getExpirationDateTime() {
		return this.expirationDateTime;
	}

	@Override
	public void setExpirationDateTime(final LocalDateTime expirationDateTime) {
		this.expirationDateTime = expirationDateTime;
	}

	@Override
	public T getTarget() {
		return this.target;
	}

	@Override
	public boolean isExpired() {
		return LocalDateTime.now().isAfter(this.expirationDateTime);
	}

}
