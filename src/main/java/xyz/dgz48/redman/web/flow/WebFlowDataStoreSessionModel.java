package xyz.dgz48.redman.web.flow;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

/**
 * ウェブフローデータオブジェクトのデータストア.
 *
 * @author win2cot
 */
@SessionScope
@Component
public class WebFlowDataStoreSessionModel implements Serializable {

	/**
	 * シリアルバージョンUID.
	 */
	private static final long serialVersionUID = 8121036351321655042L;

	/** ロガー. */
	private static final Logger LOG = LoggerFactory.getLogger(WebFlowDataStoreSessionModel.class);

	/** WFD NOT_FOUNDエラーログメッセージ. */
	private static final String WFD_LIFECYCLE_NOT_FOUND_MESSAGE = "[WFD NOT_FOUND] fid={}";

	/** WFD EXPIREDエラーログメッセージ. */
	private static final String WFD_LIFECYCLE_EXPIRED_MESSAGE = "[WFD EXPIRED] fid={}";

	/**
	 * 機能フローデータマップ.
	 */
	private final Map<String, ExpirableWebFlowData> webFlowDataMap = new HashMap<>();

	/**
	 * 機能フローデータを作成して返す.
	 *
	 * @param <T> 機能フローデータの型
	 * @param clazz 機能フローデータクラス
	 * @return 新しく作成された機能フローデータ
	 */
	public synchronized <T extends WebFlowData> T createWebFlowData(final Class<T> clazz) {

		// フロー識別子を作成する
		String fid = this.generateFid();

		// 機能フローデータを作成する
		T wfd;
		try {
			wfd = clazz.getDeclaredConstructor(String.class).newInstance(fid);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		// 有効期限を設定する
		ExpirableWebFlowData ewfd = new ExpirableWebFlowData(wfd, this.calculateWfdExpirationData());

		// 登録する
		this.webFlowDataMap.put(fid, ewfd);

		return wfd;
	}

	/**
	 * 機能フローデータを返す.
	 *
	 * @param <T> 機能フローデータの型
	 * @param fid フロー識別子
	 * @return 機能フローデータ
	 * @throws InvalidOperationException 同時操作など無効な操作を行った場合
	 */
	public synchronized <T extends WebFlowData> T getWebFlowData(final String fid)
			throws InvalidOperationException {

		// 有効期限付きウェブフローデータを探す
		ExpirableWebFlowData ewfd = this.webFlowDataMap.get(fid);
		if (ewfd == null) {
			// 見つからないので無効操作
			LOG.debug(WFD_LIFECYCLE_NOT_FOUND_MESSAGE, fid);
			throw new InvalidOperationException("WebFlowDataが見つかりません。fid=" + fid);
		}

		if (ewfd.isExpired()) {
			// 期限切れなので無効操作
			LOG.debug(WFD_LIFECYCLE_EXPIRED_MESSAGE, fid);
			throw new InvalidOperationException("WebFlowDataが有効期限切れです。fid=" + fid);
		}

		// ウェブフローデータの有効期限を伸ばす
		ewfd.setExpirationDateTime(this.calculateWfdExpirationData());

		@SuppressWarnings("unchecked")
		T wfd = (T) ewfd.getTarget();

		return wfd;
	}

	/**
	 * 機能フローデータを破棄する.
	 *
	 * @param fid フロー識別子
	 */
	public synchronized void discardWebFlowData(final String fid) {
		this.webFlowDataMap.remove(fid);
	}

	/**
	 * 機能フローデータの有効期限を計算して返す.
	 *
	 * @return 機能フローデータの有効期限
	 */
	private LocalDateTime calculateWfdExpirationData() {
		// TODO 有効期限は設定から取る
		TemporalUnit unit = ChronoUnit.MINUTES;
		long amount = 20L;

		return this.calculateExpirationDate(LocalDateTime.now(), unit, amount);
	}

	/**
	 * 有効期限を計算して返す.
	 *
	 * @param base 基準となる日時
	 * @param unit {@link TemporalUnit}
	 * @param amount 延長量
	 * @return 有効期限
	 */
	private LocalDateTime calculateExpirationDate(final LocalDateTime base, final TemporalUnit unit, final long amount) {
		return base.plus(amount, unit);
	}

	/**
	 * 新しいフロー識別子を生成して返す.
	 *
	 * @return 新しいフロー識別子
	 */
	private String generateFid() {
		return this.generateRandomString("F");
	}

	/**
	 * ランダムな文字列を生成して返す.
	 *
	 * @param prefix 接頭辞
	 * @return ランダムな文字列
	 */
	private String generateRandomString(final String prefix) {
		return prefix + UUID.randomUUID().toString();
	}
}
