package xyz.dgz48.redman.domain.resource;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.Set;

/**
 * Definition of connection for one Http access.
 */
@AllArgsConstructor
@Value
public class ResourceEntity {

	/**
	 * Identifier.
	 */
	private String resourceId;

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
