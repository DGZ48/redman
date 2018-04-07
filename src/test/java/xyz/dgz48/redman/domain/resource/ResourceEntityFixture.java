package xyz.dgz48.redman.domain.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Fixture for resource entity.
 */

public final class ResourceEntityFixture {

	/**
	 * Utility.
	 */
	private ResourceEntityFixture() {

	}

	/**
	 * Create test resource.
	 * @param userId userId
	 * @param resourceId resourceId
	 * @return entity
	 * @throws JsonProcessingException can not deserialize
	 */
	public static ResourceEntity create(final String userId, final String resourceId) throws JsonProcessingException {

		List<HttpHeader> headers = new ArrayList<>();
		headers.add(new HttpHeader("name1", "value1"));
		headers.add(new HttpHeader("name2", "value2"));
		return new ResourceEntity(resourceId,
				userId, "name",
				HttpMethod.GET, "URL",
				new ObjectMapper().writeValueAsString(headers),
				"{\"key\" : \"value\"}");
	}

}
