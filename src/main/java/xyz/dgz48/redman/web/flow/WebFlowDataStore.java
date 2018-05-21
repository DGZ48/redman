package xyz.dgz48.redman.web.flow;

/**
 * ウェブフローデータ領域.
 *
 * @author win2cot
 */
public interface WebFlowDataStore {

	/**
	 * ウェブフローデータを返す.
	 *
	 * @param <T> ウェブフローデータの型
	 * @return ウェブフローデータ
	 */
	<T extends WebFlowData> T getWebFlowData();

}
