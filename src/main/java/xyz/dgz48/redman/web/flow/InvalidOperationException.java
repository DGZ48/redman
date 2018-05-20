package xyz.dgz48.redman.web.flow;

/**
 * 無効操作例外.
 *
 * @author win2cot
 */
public class InvalidOperationException extends RuntimeException {

	/**
	 * コンストラクタ.
	 *
	 * @param message 詳細メッセージ
	 */
	InvalidOperationException(final String message) {
		super(message);
	}

	/**
	 * コンストラクタ.
	 *
	 * @param cause 原因。(null値が許可されており、原因が存在しないか不明であることを示す。)
	 */
	InvalidOperationException(final Throwable cause) {
		super(cause);
	}

	/**
	 * コンストラクタ.
	 *
	 * @param message 詳細メッセージ
	 * @param cause 原因。(null値が許可されており、原因が存在しないか不明であることを示す。)
	 */
	InvalidOperationException(final String message, final Throwable cause) {
		super(message, cause);
	}


	/**
	 * コンストラクタ.
	 *
	 * @param message 詳細メッセージ
	 * @param cause 原因。(null値が許可されており、原因が存在しないか不明であることを示す。)
	 * @param enableSuppression 抑制を有効化するか、それとも無効化するか
	 * @param writableStackTrace スタック・トレースを書込み可能にするかどうか
	 */
	InvalidOperationException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
