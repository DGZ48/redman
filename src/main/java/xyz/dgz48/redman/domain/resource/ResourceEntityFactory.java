package xyz.dgz48.redman.domain.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Factory for {@link ResourceEntity}.
 */
@Component
public class ResourceEntityFactory {

	/**
	 * ObjectMapper.
	 */
	@Autowired
	ObjectMapper mapper;

	public ResourceEntity create(Resource resource) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return new ResourceEntity(resource.getResourceId(),
				resource.getUserId(),
				resource.getName(),
				resource.getHttpMethod(),
				resource.getUrl(),
				mapper.writeValueAsString(resource.getHeaders()),
				resource.getBody());
	}

}
