package xyz.dgz48.redman.web.resource;

import lombok.Data;

@Data
public class HttpHeaderRequest {

	/**
	 * Header name.
	 */
	private String name;

	/**
	 * Header value.
	 */
	private String value;
}
