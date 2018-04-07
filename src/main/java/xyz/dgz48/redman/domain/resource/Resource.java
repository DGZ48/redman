package xyz.dgz48.redman.domain.resource;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import java.util.List;

import lombok.Value;

/**
 * Definition of connection for one Http access.
 */
@Value
@SuppressFBWarnings("RCN_REDUNDANT_NULLCHECK_OF_NONNULL_VALUE")
public class Resource {

	/**
	 * Identifier.
	 */
	private final String resourceId;

	/**
	 * Resource owner identifier.
	 */
	private final String userId;

	/**
	 * Resource name.
	 */
	private final String name;

	/**
	 * Http method.
	 */
	private final HttpMethod httpMethod;

	/**
	 * URL.
	 */
	private final String url;

	/**
	 * Http headers.
	 */
	private final List<HttpHeader> headers;

	/**
	 * Http body.
	 */
	private final String body;

	/**
	 * Constructor.
	 * @param resourceId resourceId
	 * @param userId userId
	 * @param name name
	 * @param httpMethod httpMethod
	 * @param url url
	 * @param headers headers
	 * @param body body
	 */
	public Resource(final String resourceId, final String userId,
					final String name, final HttpMethod httpMethod,
					final String url, final List<HttpHeader> headers,
					final String body) {
		this.resourceId = resourceId;
		this.userId = userId;
		this.name = name;
		this.httpMethod = httpMethod;
		this.url = url;
		this.headers = new ArrayList<>(headers);
		this.body = body;
	}

	/**
	 * Get Headers.
	 * @return headers
	 */
	public List<HttpHeader> getHeaders() {
		return new ArrayList<>(headers);
	}

}
