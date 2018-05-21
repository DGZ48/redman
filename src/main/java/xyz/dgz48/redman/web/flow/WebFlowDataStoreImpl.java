package xyz.dgz48.redman.web.flow;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * Implementation of {@link WebFlowDataStore}.
 *
 * @author win2cot
 */
public class WebFlowDataStoreImpl implements WebFlowDataStore, HandlerInterceptor {

	/** ロガー. */
	private static final Logger LOG = LoggerFactory.getLogger(WebFlowDataStoreImpl.class);

	/** WFD開始ログメッセージ. */
	private static final String WFD_LIFECYCLE_START_MESSAGE = "[WFD START] fid={} class={}";

	/** WFD継続ログメッセージ. */
	private static final String WFD_LIFECYCLE_JOIN_MESSAGE = "[WFD JOIN] fid={} class={}";

	/**
	 * ウェブフローデータオブジェクトのデータストア.
	 */
	@Autowired
	private WebFlowDataStoreSessionModel dataStoreSessionModel;

	/**
	 * フローID.
	 */
	private ThreadLocal<String> fidHolder = new ThreadLocal<>();

	@Override
	public <T extends WebFlowData> T getWebFlowData() {

		String fid = this.fidHolder.get();

		return this.dataStoreSessionModel.getWebFlowData(fid);
	}

	/**
	 * ウェブフローデータを返す.
	 *
	 * @param fid フローID
	 * @param handlerMethod ハンドラメソッド（コントローラメソッド）
	 * @return ウェブフローデータ
	 */
	private WebFlowData getWebFlowData(final String fid, final HandlerMethod handlerMethod) {

		FlowBegin flowBegin = handlerMethod.getMethodAnnotation(FlowBegin.class);
		FlowRequired flowRequired = handlerMethod.getMethodAnnotation(FlowRequired.class);
		FlowEnd flowEnd = handlerMethod.getMethodAnnotation(FlowEnd.class);

		boolean fidRequested = !Strings.isEmpty(fid);


		if (flowRequired != null) {
			if (fidRequested) {
				WebFlowData wfd = this.dataStoreSessionModel.getWebFlowData(fid);
				wfd.setNewCreated(false);
				LOG.debug(WFD_LIFECYCLE_JOIN_MESSAGE, fid, wfd.getClass().getSimpleName());
				return wfd;
			} else {
				throw new InvalidOperationException(handlerMethod.getShortLogMessage() + "に@FlowRequiredが付与されていますが、fidが送信されていません");
			}
		} else if (flowEnd != null) {
			if (fidRequested) {
				WebFlowData wfd = this.dataStoreSessionModel.getWebFlowData(fid);
				wfd.setNewCreated(false);
				LOG.debug(WFD_LIFECYCLE_JOIN_MESSAGE, fid, wfd.getClass().getSimpleName());
				return wfd;
			} else {
				throw new InvalidOperationException(handlerMethod.getShortLogMessage() + "に@FlowEndが付与されていますが、fidが送信されていません");
			}
		} else if (flowBegin != null) {
			if (fidRequested) {
				LOG.warn("{}に@FlowBeginが付与されていますが、fidが送信されています", handlerMethod.getShortLogMessage());
			}

			WebFlowData wfd = this.dataStoreSessionModel.createWebFlowData(flowBegin.dataClass());
			wfd.setNewCreated(true);
			LOG.debug(WFD_LIFECYCLE_START_MESSAGE, fid, wfd.getClass().getSimpleName());
			return wfd;

		} else {
			return null;
		}

	}

	/*
	 * (非 Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response,
			final Object handler) throws Exception {

		HandlerMethod handlerMethod;
		if (handler instanceof HandlerMethod) {
			handlerMethod = (HandlerMethod) handler;
		} else {
			return true;
		}

		// ウェブフロー識別子
		String fid = request.getParameter("fid");

		// 機能フローデータ
		WebFlowData wfd = this.getWebFlowData(fid, handlerMethod);
		if (wfd != null) {
			this.fidHolder.set(wfd.getFid());
			request.setAttribute("fid", wfd.getFid());
		}

		return true;
	}

	/*
	 * (非 Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(final HttpServletRequest request, final HttpServletResponse response,
			final Object handler, final ModelAndView modelAndView) throws Exception {
		// NOP
	}

	/*
	 * (非 Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	@Override
	public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response,
			final Object handler, final Exception ex) throws Exception {
		this.fidHolder.remove();
	}

}
