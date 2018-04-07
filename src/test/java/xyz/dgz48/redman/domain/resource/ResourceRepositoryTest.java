package xyz.dgz48.redman.domain.resource;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.dgz48.redman.domain.user.IdpType;
import xyz.dgz48.redman.domain.user.UserEntity;
import xyz.dgz48.redman.domain.user.UserRepository;



/**
 * Test for {@link ResourceRepository}.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class ResourceRepositoryTest {

	/**
	 * Test target.
	 */
	@Autowired
	private ResourceRepository sut;

	/**
	 * Repository for User.
	 */
	@Autowired
	private UserRepository userRepository;

	/**
	 * Test for findByIdpUserName.
	 *
	 * @throws Exception exception
	 */
	@Test
	public void testFindByIdpUserNameNotExistMatchOnlyIdpType() throws Exception {
		// setup
		String targetUserId = "target_user_id";
		String otherUserId = "other_user_id";

		this.userRepository.save(new UserEntity(targetUserId, "name1", "test1@example.com", IdpType.GITHUB));
		this.userRepository.save(new UserEntity(otherUserId, "name2", "test2@example.com", IdpType.GITHUB));

		// register 3 resource but 1 resource's owner is not target user.
		ResourceEntity resourceEntity1 = ResourceEntityFixture.create(targetUserId, "resource_id1");
		ResourceEntity resourceEntity2 = ResourceEntityFixture.create(targetUserId, "resource_id2");
		ResourceEntity resourceEntity3 = ResourceEntityFixture.create(otherUserId, "resource_id3");
		this.sut.save(resourceEntity1);
		this.sut.save(resourceEntity2);
		this.sut.save(resourceEntity3);

		// exercise
		Page<ResourceEntity> actual = this.sut.findByUserId(targetUserId, PageRequest.of(0, 2));

		// verify
		assertThat(actual).hasSize(2);
		assertThat(actual).contains(resourceEntity1, resourceEntity2);
	}
}
