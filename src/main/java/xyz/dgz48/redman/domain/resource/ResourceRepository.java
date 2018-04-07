package xyz.dgz48.redman.domain.resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for resource.
 */
public interface ResourceRepository extends JpaRepository<ResourceEntity, String> {

	/**
	 * Find {@link ResourceEntity} resource by idpUserName(Spring security username).
	 * @param userId user id
	 * @param pageable pageable
	 * @return user
	 */
	Page<ResourceEntity> findByUserId(String userId, Pageable pageable);
}
