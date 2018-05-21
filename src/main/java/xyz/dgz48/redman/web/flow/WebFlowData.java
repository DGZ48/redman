package xyz.dgz48.redman.web.flow;

/**
 * ウェブフローデータ基底クラス.
 *
 * @author win2cot
 *
 */
public abstract class WebFlowData {

	/**
	 * フローID.
	 */
	private final String fid;

	/**
	 * 新規作成フラグ.
	 */
	private boolean newCreated;

	/**
	 * コンストラクタ.
	 *
	 * @param fid フローID
	 */
	public WebFlowData(final String fid) {
		super();
		this.fid = fid;
	}

	/**
	 * フローIDを返す.
	 *
	 * @return フローID
	 */
	public String getFid() {
		return fid;
	}

	/**
	 * 新規作成フラグを返す.
	 *
	 * @return 新規作成フラグ
	 */
	public boolean isNewCreated() {
		return newCreated;
	}

	/**
	 * 新規作成フラグを設定する.
	 *
	 * @param newCreated 新規作成フラグ
	 */
	void setNewCreated(final boolean newCreated) {
		this.newCreated = newCreated;
	}


}
