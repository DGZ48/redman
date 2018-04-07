package xyz.dgz48.redman.domain.resource;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;

import org.springframework.stereotype.Component;


/**
 * Factory for {@link Resource}.
 */
@Component
@SuppressFBWarnings("SIC_INNER_SHOULD_BE_STATIC_ANON")
public class ResourceFactory {

	/**
	 * Objectmapper.
	 */
	private final ObjectMapper mapper = new ObjectMapper();

	/**
	 * Create {@link Resource} by {@link ResourceEntity}.
	 * @param resourceEntity resourceEntity
	 * @return resource
	 * @throws IOException can not deserialize headers
	 */
	public Resource create(final ResourceEntity resourceEntity) {
		try {
			return new Resource(resourceEntity.getResourceId(),
					resourceEntity.getUserId(),
					resourceEntity.getName(),
					resourceEntity.getHttpMethod(),
					resourceEntity.getUrl(),
					mapper.readValue(resourceEntity.getHeaders(), new TypeReference<List<HttpHeader>>() { }),
					resourceEntity.getBody());
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}
}
