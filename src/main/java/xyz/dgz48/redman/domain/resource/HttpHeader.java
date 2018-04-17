package xyz.dgz48.redman.domain.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * Http Header.
 */
@Data
public class HttpHeader {

	/**
	 * Header name.
	 */
	@JsonProperty("name")
	private String name;

	/**
	 * Header value.
	 */
	@JsonProperty("value")
	private String value;
}
