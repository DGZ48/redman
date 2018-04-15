package xyz.dgz48.redman.domain.resource;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
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
public class ResourceEntityFactoryTest {

	/**
	 * Test target.
	 */
	@Autowired
	private ResourceEntityFactory sut;

	/**
	 * Test for create resource.
	 * @throws Exception Exception
	 */
	@Test
	public void create() throws Exception { // NOPMD
		// setup
		List<HttpHeader> headers = new ArrayList<>();
		headers.add(new HttpHeader("name1", "value1"));
		headers.add(new HttpHeader("name2", "value2"));

		Resource resource = new Resource("resource_id", "user_id",
				"name", HttpMethod.GET,
				"http://example.com",
				headers, "body");

		// exercise
		ResourceEntity actual = sut.create(resource);

		// verify
		assertThat(actual).isEqualTo(
				new ResourceEntity(resource.getResourceId(), resource.getUserId(),
						resource.getName(), resource.getHttpMethod(),
						resource.getUrl(), new ObjectMapper().writeValueAsString(resource.getHeaders()),
						resource.getBody()));
	}

}
