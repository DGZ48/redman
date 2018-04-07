package xyz.dgz48.redman.domain.resource;

import lombok.Value;

/**
 * Http Header.
 */
@Value
public class HttpHeader {

	/**
	 * Header name.
	 */
	private String name;

	/**
	 * Header value.
	 */
	private String value;
}
