package upce.nnpia.blog.controllerTests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import upce.nnpia.blog.dao.RoleDao;
import upce.nnpia.blog.dao.UserDao;
import upce.nnpia.blog.entity.RoleType;
import upce.nnpia.blog.entity.User;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void shouldCreateMockMvc() {
        assertNotNull(mockMvc);

        User user = new User();
        user.setRole(roleDao.findByRoleName(RoleType.ROLE_USER));
        user.setUsername("test");
        user.setLastName("lastname");
        user.setFirstName("firstname");
        user.setPassword(passwordEncoder.encode("test"));

        userDao.saveAndFlush(user);
    }

    @AfterEach
    public void teardown() {
        userDao.deleteAll();
    }

    @Test
    public void login() throws Exception {
        String content = mockMvc.perform(post("/authenticate").contentType(MediaType.APPLICATION_JSON)
                .content(String.format("{ \"username\":\"%s\", \"password\":\"%s\" }", "test", "test"))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn()
                .getResponse().getContentAsString();
        Assertions.assertTrue(content.contains("token"));
    }

    @Test
    public void registration() throws Exception {
        String response = mockMvc.perform(post("/registration").contentType(MediaType.APPLICATION_JSON)
                .content(String.format("{ \"firstName\":\"%s\", \"lastName\":\"%s\", \"username\":\"%s\", \"password\":\"%s\" }", "testFirstname", "testLastname", "testUsername", "testPassword"))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn()
                .getResponse().getContentAsString();
        Assertions.assertTrue(response.contains("User was added."));
    }
}
