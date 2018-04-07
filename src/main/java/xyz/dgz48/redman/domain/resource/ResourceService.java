package xyz.dgz48.redman.domain.resource;

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


	/**
	 * Upsert new {@link Resource}.
	 * @param resource target
	 * @return upserted resource
	 */
	public Resource saveResource(final Resource resource){
		log.info("Save user:{}", resource);
//		return repository.save(resourceEntityFactory.create(resource));
		return null;
	}
}
