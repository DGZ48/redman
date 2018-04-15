package xyz.dgz48.redman.domain.resource;

import javax.persistence.*;
import lombok.*;
import xyz.dgz48.redman.domain.user.Ownable;

/**
 * Definition of connection for one Http access.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "resource")
@EqualsAndHashCode
public class ResourceEntity implements Ownable {

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
