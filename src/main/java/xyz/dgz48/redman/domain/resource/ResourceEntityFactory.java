package xyz.dgz48.redman.domain.resource;

import org.springframework.stereotype.Component;

/**
 * Factory for {@link ResourceEntity}.
 */
@Component
public class ResourceEntityFactory {

	public ResourceEntity create(Resource resource) {
		return new ResourceEntity(resource.getResourceId(),
				resource.getName(),
				resource.getHttpMethod(),
				resource.getUrl(),
				resource.getHeaders(),
				resource.getBody());
	}

}
