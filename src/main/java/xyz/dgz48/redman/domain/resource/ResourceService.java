package xyz.dgz48.redman.domain.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for {@link Resource}.
 */
@Service
@Slf4j
public class ResourceService {

	@Autowired
	ResourceRepository repository;

	@Autowired
	ResourceEntityFactory resourceEntityFactory;

	@Autowired
	ResourceFactory resourceFactory;

	/**
	 * Upsert new {@link Resource}.
	 * @param resource target
	 * @return upserted resource
	 */
	public Resource saveResource(final Resource resource) throws JsonProcessingException {
		log.info("Save user:{}", resource);
		return resourceFactory.create(repository.save(resourceEntityFactory.create(resource)));
	}
}
