package xyz.dgz48.redman.domain.resource;

import java.io.IOException;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import xyz.dgz48.redman.domain.auth.AccessPriviledgeVerifier;
import xyz.dgz48.redman.domain.user.UserService;

/**
 * Service for {@link Resource}.
 */
@Service
@Slf4j
public class ResourceService {

	/**
	 * ResourceRepository.
	 */
	@Autowired
	private ResourceRepository repository;

	/**
	 * ResourceEntityFactory.
	 */
	@Autowired
	private ResourceEntityFactory resourceEntityFactory;

	/**
	 * ResourceFactory.
	 */
	@Autowired
	private ResourceFactory resourceFactory;

	/**
	 * Owner verifier.
	 */
	@Autowired
	private AccessPriviledgeVerifier accessPriviledgeVerifier;

	/**
	 * User service.
	 */
	@Autowired
	private UserService userService;

	/**
	 * Upsert new {@link Resource}.
	 * @param resource target
	 * @return upserted resource
	 * @throws IOException exception
	 */
	public Resource saveResource(final Resource resource) throws IOException {
		log.info("Save resource:{}", resource);

		accessPriviledgeVerifier.verifyAccessPrivilege(userService.findLoginUser().getUserId(), resource);

		return resourceFactory.create(repository.save(resourceEntityFactory.create(resource)));
	}

	/**
	 * Delete resource.
	 * @param resourceId resourceId
	 * @return deleted resource
	 */
	public Optional<Resource> deleteResource(final String resourceId) {
		log.info("Delete resource:{}", resourceId);
		Optional<ResourceEntity> resourceEntity = repository.findById(resourceId);

		resourceEntity.ifPresent(e -> accessPriviledgeVerifier.verifyAccessPrivilege(userService.findLoginUser().getUserId(), e));

		resourceEntity.ifPresent(e -> repository.deleteById(e.getResourceId()));
		return resourceEntity.map(e -> resourceFactory.create(e));
	}

	/**
	 * Find by user id.
	 * @param userId userId
	 * @param pageable pageable request
	 * @return find results
	 */
	public Page<Resource> findByUserId(final String userId, final Pageable pageable) {
		Page<ResourceEntity> byUserId = repository.findByUserId(userId, pageable);
		accessPriviledgeVerifier.verifyAccessPrivilege(userService.findLoginUser().getUserId(), byUserId);
		return byUserId.map(e -> resourceFactory.create(e));
	}

	/**
	 * Find by resourceId.
	 * @param resourceId resourceId
	 * @return find result
	 */
	public Optional<Resource> findByResourceId(final String resourceId) {
		Optional<ResourceEntity> entity = repository.findById(resourceId);
		accessPriviledgeVerifier.verifyAccessPrivilege(userService.findLoginUser().getUserId(), entity);
		return entity.map(e -> resourceFactory.create(e));
	}

}
