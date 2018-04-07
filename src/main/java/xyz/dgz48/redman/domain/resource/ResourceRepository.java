package xyz.dgz48.redman.domain.resource;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for resource.
 */
public interface ResourceRepository extends JpaRepository<ResourceEntity, String> {

	/**
	 * Find {@link ResourceEntity} resource by idpUserName(Spring security username).
	 * @param userId user id
	 * @return user
	 */
	List<ResourceEntity> findByUserId(String userId);
}
