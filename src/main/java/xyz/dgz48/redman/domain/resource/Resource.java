package xyz.dgz48.redman.domain.resource;

import java.util.Set;
import lombok.Value;

/**
 * Definition of connection for one Http access.
 */
@Value
public class Resource {

	/**
	 * Identifier.
	 */
	private String resourceId;

	/**
	 * Resource owner identifier.
	 */
	private String userId;

	/**
	 * Resource name.
	 */
	private String name;

	/**
	 * Http method.
	 */
	private HttpMethod httpMethod;

	/**
	 * URL.
	 */
	private String url;

	/**
	 * Http headers;
	 */
	private Set<HttpHeader> headers;

	/**
	 * Http body.
	 */
	private String body;
}
