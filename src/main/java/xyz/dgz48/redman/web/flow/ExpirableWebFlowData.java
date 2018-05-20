package xyz.dgz48.redman.web.flow;

import java.time.LocalDateTime;

/**
 * 有効期限付きウェブフローデータ.
 *
 * @author win2cot
 */
class ExpirableWebFlowData extends AbstractExpirable<WebFlowData> {

	/**
	 * コンストラクタ.
	 *
	 * @param target ウェブフローデータ
	 * @param expirationDate 有効期限
	 */
	ExpirableWebFlowData(final WebFlowData target, final LocalDateTime expirationDate) {
		super(target, expirationDate);
	}

}
