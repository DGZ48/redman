package xyz.dgz48.redman.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


/**
 * Test for {@link LoginController}.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {

    /**
     * MockMvc.
     */
    @Autowired
    private MockMvc mockMvc;

    /**
     * Test for index page.
     *
     * @throws Exception exception
     */
    @Test
    public void index() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().is3xxRedirection());
    }

    /**
     * Test for login page.
     *
     * @throws Exception exception
     */
    @Test
    public void viewLogin() throws Exception {
        mockMvc.perform(get("/login")).andExpect(status().isOk());
    }

}
