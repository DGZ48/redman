package xyz.dgz48.redman.domain.resource;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Factory for {@link Resource}.
 */
public class ResourceFactory {

	/**
	 * Objectmapper.
	 */
	@Autowired
	ObjectMapper mapper;

	/**
	 * Create {@link Resource} by {@link ResourceEntity}.
	 * @param resourceEntity resourceEntity
	 * @return resource
	 */
	public Resource create(ResourceEntity resourceEntity) {
		return new Resource(resourceEntity.getResourceId(),
				resourceEntity.getUserId(),
				resourceEntity.getName(),
				resourceEntity.getHttpMethod(),
				resourceEntity.getUrl(),
				mapper.convertValue(resourceEntity.getHeaders(), new TypeReference<List<HttpHeader>>() {}),
				resourceEntity.getBody());
	}
}
