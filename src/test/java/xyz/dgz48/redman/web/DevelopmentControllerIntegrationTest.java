package xyz.dgz48.redman.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


/**
 * Test for {@link DevelopmentController}.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Profile("debug")
public class DevelopmentControllerIntegrationTest {

	/**
	 * MockMvc.
	 */
	@Autowired
	private MockMvc mockMvc;

	/**
	 * Test for /starter.html.
	 *
	 * @throws Exception exception
	 */
	@Test
	@WithMockOAuth2User()
	public void testViewStarter() throws Exception { // NOPMD
		mockMvc.perform(get("/starter.html")).andExpect(status().isOk());
	}

	/**
	 * Test for /example/bootstrap.html.
	 *
	 * @throws Exception exception
	 */
	@Test
	@WithMockOAuth2User()
	public void testViewExampleBootsrap() throws Exception { // NOPMD
		mockMvc.perform(get("/example/bootstrap.html")).andExpect(status().isOk());
	}

	/**
	 * Test for /example/bootstrap-honoka.html.
	 *
	 * @throws Exception exception
	 */
	@Test
	@WithMockOAuth2User()
	public void testViewExampleBootsrapHonoka() throws Exception { // NOPMD
		mockMvc.perform(get("/example/bootstrap-honoka.html")).andExpect(status().isOk());
	}

	/**
	 * Test for /example/bootstrap-honoka2.html.
	 *
	 * @throws Exception exception
	 */
	@Test
	@WithMockOAuth2User()
	public void testViewExampleBootsrapHonoka2() throws Exception { // NOPMD
		mockMvc.perform(get("/example/bootstrap-honoka2.html")).andExpect(status().isOk());
	}

	/**
	 * Test for /example/bootstrap-googlewebfont.html.
	 *
	 * @throws Exception exception
	 */
	@Test
	@WithMockOAuth2User()
	public void testExampleBootsrapGoogleWebFont() throws Exception { // NOPMD
		mockMvc.perform(get("/example/bootstrap-googlewebfont.html")).andExpect(status().isOk());
	}

}
