package xyz.dgz48.redman.domain.user.resource;

import lombok.extern.slf4j.Slf4j;

/**
 * Service for {@link Resource}.
 */
@Slf4j
public class ResourceService {

	/**
	 * Upsert new {@link Resource}.
	 * @param resource target
	 * @return upserted resource
	 */
	public Resource saveResource(final Resource resource){
		log.info("Save user:{}", resource);

		return null;
	}
}
