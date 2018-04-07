package xyz.dgz48.redman.domain.resource;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for resource.
 */
public interface ResourceRepository extends JpaRepository<ResourceEntity, String> {
}
