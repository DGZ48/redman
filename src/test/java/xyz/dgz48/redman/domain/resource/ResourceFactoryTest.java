package xyz.dgz48.redman.domain.resource;

import java.io.UncheckedIOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * Test for {@link ResourceEntityFactory}.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ResourceFactoryTest {

	/**
	 * Test target.
	 */
	@Autowired
	private ResourceFactory sut;

	/**
	 * Test for create.
	 * @throws Exception Exception
	 */
	@Test
	public void create() throws Exception { // NOPMD
		ResourceEntity resourceEntity = ResourceEntityFixture.create("user_id", "resource_id");
		sut.create(resourceEntity);
	}

	/**
	 * Test for create.
	 * @throws Exception Exception
	 */
	@Test(expected = UncheckedIOException.class)
	public void createThrowsException() throws Exception { // NOPMD
		ResourceEntity resourceEntity = new ResourceEntity("resource_id",
				"user_id", "name",
				HttpMethod.GET, "URL",
				"invalid json",
				"{\"key\" : \"value\"}");

		sut.create(resourceEntity);
	}
}
