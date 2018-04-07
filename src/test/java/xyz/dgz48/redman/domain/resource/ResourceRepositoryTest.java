package xyz.dgz48.redman.domain.resource;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.dgz48.redman.domain.user.IdpType;
import xyz.dgz48.redman.domain.user.UserEntity;
import xyz.dgz48.redman.domain.user.UserRepository;

import java.util.List;
import java.util.Optional;


/**
 * Test for {@link ResourceRepository}.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class ResourceRepositoryTest {

	/**
	 * Test data.
	 */
	private final ResourceEntity expect = new ResourceEntity("resource_id", "user_id", "name", HttpMethod.GET, "URL", "[{\"key\" : \"value\"}]", "[{\"key\" : \"value\"}]");

	/**
	 * Test target.
	 */
	@Autowired
	private ResourceRepository sut;

	/**
	 * Test for findByIdpUserName.
	 *
	 * @throws Exception exception
	 */
	@Test
	public void testFindByIdpUserNameNotExistMatchOnlyIdpType() throws Exception {
		// exercise
		this.sut.save(expect);

		// exercise
		List<ResourceEntity> actual = this.sut.findByUserId(expect.getUserId());

		// verify
		assertThat(actual).isNotEmpty();
	}
}
