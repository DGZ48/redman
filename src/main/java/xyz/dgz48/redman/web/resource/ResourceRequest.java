package xyz.dgz48.redman.web.resource;

import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
public class ResourceRequest {

	/**
	 * Resource name.
	 */
	private final String name;

	/**
	 * Http method.
	 */
	private final String httpMethod;

	/**
	 * URL.
	 */
	private final String url;

	/**
	 * Http headers.
	 */
	private final List<HttpHeaderRequest> headers = new ArrayList<>();

	/**
	 * Http body.
	 */
	private final String body;
}
