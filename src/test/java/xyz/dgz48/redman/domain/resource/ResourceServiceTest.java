package xyz.dgz48.redman.domain.resource; // NOPMD

import static org.assertj.core.api.Java6Assertions.assertThat; // NOPMD
import static org.mockito.ArgumentMatchers.anyString; // NOPMD
import static org.mockito.Mockito.times; // NOPMD
import static org.mockito.Mockito.verify; // NOPMD
import static org.mockito.Mockito.when; // NOPMD

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;


/**
 * Test for {@link xyz.dgz48.redman.domain.resource.ResourceService}.
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ResourceServiceTest {

	/**
	 * Test target.
	 */
	@InjectMocks
	private ResourceService sut;

	/**
	 * {@link Resource} factory.
	 */
	@Spy
	private ResourceFactory resourceFactory;

	/**
	 * {@link ResourceEntity} factory.
	 */
	@Spy
	private ResourceEntityFactory userEntityFactory; // NOPMD

	/**
	 * Mock repository for {@Resource}.
	 */
	@Mock
	private ResourceRepository resourceRepository;

	/**
	 * user id.
	 */
	private static final String USER_ID = "user_id";

	/**
	 * user name.
	 */
	private static final String USER_NAME  = "user_name";

	/**
	 * Test for upsertResource.(Not exist entity).
	 * @throws IOException IOException
	 */
	@Test
	public void upsertResourceInsert() throws IOException {
		// set up
		ResourceEntity resourceEntity = ResourceEntityFixture.create(USER_ID, USER_NAME);
		when(resourceRepository.save(ResourceEntityFixture.create(USER_ID, USER_NAME))).thenReturn(resourceEntity);

		// exercise
		Resource actual = sut.saveResource(this.resourceFactory.create(resourceEntity));

		// verify
		verify(resourceRepository, times(1)).save(resourceEntity);
		assertThat(actual).isEqualTo(this.resourceFactory.create(resourceEntity));
	}

	/**
	 * Test for findByUserId.
	 *
	 * @throws JsonProcessingException JsonProcessingException
	 */
	@Test
	public void findByUserId() throws JsonProcessingException {
		// setup
		List<ResourceEntity> resourceEntityList = new ArrayList<>();
		resourceEntityList.add(ResourceEntityFixture.create(USER_ID, "resource_id"));
		when(resourceRepository.findByUserId(USER_ID, PageRequest.of(1, 1))).thenReturn(new PageImpl(resourceEntityList));

		// exercise
		sut.findByUserId(USER_ID, PageRequest.of(1, 1));

		// verify
		verify(resourceRepository, times(1)).findByUserId("user_id", PageRequest.of(1, 1));
	}

	/**
	 * Test for findById.
	 * @throws IOException exception
	 */
	@Test
	public void findById() throws IOException {
		// setup
		ResourceEntity resourceEntity = ResourceEntityFixture.create(USER_ID, USER_NAME);
		when(resourceRepository.findById(resourceEntity.getResourceId())).thenReturn(Optional.of(resourceEntity));

		// exercise
		Optional<Resource> actual = sut.findByResourceId(resourceEntity.getResourceId());

		// verify
		verify(resourceRepository, times(1)).findById(resourceEntity.getResourceId());
		assertThat(actual.get()).isEqualTo(resourceFactory.create(resourceEntity));
	}

	/**
	 * Test for deleteResource.
	 */
	@Test
	public void deleteResourceNotExist() {
		// exercise
		Optional<Resource> actual = sut.deleteResource("noting");

		// verify
		assertThat(actual.isPresent()).isEqualTo(false);
	}

	/**
	 * Test for deleteResource.
	 *
	 * @throws IOException IOException
	 */
	@Test
	public void deleteResourceExist() throws IOException {
		// setup
		ResourceEntity resourceEntity = ResourceEntityFixture.create(USER_ID, USER_NAME);
		when(resourceRepository.findById(anyString())).thenReturn(Optional.of(resourceEntity));

		// exercise
		Optional<Resource> actual = sut.deleteResource("noting");

		// verify
		assertThat(actual.get()).isEqualTo(resourceFactory.create(resourceEntity));
	}
}
