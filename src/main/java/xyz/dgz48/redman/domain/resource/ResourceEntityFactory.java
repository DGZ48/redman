package xyz.dgz48.redman.domain.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

/**
 * Factory for {@link ResourceEntity}.
 */
@Component
public class ResourceEntityFactory {

	/**
	 * Objectmapper.
	 */
	private final ObjectMapper mapper = new ObjectMapper();

	/**
	 * Create ResourceEntity.
	 * @param resource resource
	 * @return result
	 * @throws JsonProcessingException can not deserialize
	 */
	public ResourceEntity create(final Resource resource) throws JsonProcessingException {
		return new ResourceEntity(resource.getResourceId(),
				resource.getUserId(),
				resource.getName(),
				resource.getHttpMethod(),
				resource.getUrl(),
				mapper.writeValueAsString(resource.getHeaders()),
				resource.getBody());
	}
}
