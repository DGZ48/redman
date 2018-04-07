package xyz.dgz48.redman.domain.resource;

import lombok.*;

import javax.persistence.*;

/**
 * Definition of connection for one Http access.
 */
@Entity
@AllArgsConstructor
@Getter
@Table(name = "resource")
@EqualsAndHashCode
public class ResourceEntity {

	/**
	 * Identifier.
	 */
	@Id
	@Column(name = "resource_id")
	private String resourceId;

	/**
	 * Resource owner identifier.
	 */
	@Column(name = "user_id")
	private String userId;

	/**
	 * Resource name.
	 */
	@Column(name = "name")
	private String name;

	/**
	 * Http method.
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "http_method")
	private HttpMethod httpMethod;

	/**
	 * URL.
	 */
	@Column(name = "url")
	private String url;

	/**
	 * Http headers(Json array).
	 */
	@Column(name = "headers")
	private String headers;

	/**
	 * Http body.
	 */
	@Column(name = "body")
	private String body;
}
