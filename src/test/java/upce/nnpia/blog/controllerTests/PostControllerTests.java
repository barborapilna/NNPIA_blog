package upce.nnpia.blog.controllerTests;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import upce.nnpia.blog.BlogApplication;
import upce.nnpia.blog.dao.PostDao;
import upce.nnpia.blog.dao.RoleDao;
import upce.nnpia.blog.dao.UserDao;
import upce.nnpia.blog.entity.Post;
import upce.nnpia.blog.entity.Role;
import upce.nnpia.blog.entity.RoleType;
import upce.nnpia.blog.entity.User;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(classes = BlogApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PostControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostDao postDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @BeforeEach
    public void shouldCreateMockMvc() {
        assertNotNull(mockMvc);

        Role role = new Role();
        role.setRoleName(RoleType.ROLE_USER);
        roleDao.saveAndFlush(role);

        User user = new User();
        user.setRole(role);
        user.setUsername("test");
        user.setLastName("lastname");
        user.setFirstName("firstname");
        user.setPassword("test");

        userDao.saveAndFlush(user);
    }

    @Test
    public void login() throws Exception {

        String response = mockMvc.perform(post("/authenticate")
                .contentType(MediaType.APPLICATION_JSON).content(
                        "{ \"username\":\"test\", \"password\":\"test\" }"
                ).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        Assertions.assertTrue(response.contains("tokens"));
    }

    @Test
    public void getPostByID() throws Exception {
        Post post = new Post();
        post.setId(1L);
        post.setTitle("Post title");
        post.setBody("This is a post body. This is a post body. This is a post body.");

        postDao.save(post);
        Optional<Post> optionalPost = Optional.of(post);

        when(postDao.findById(1L)).thenReturn(optionalPost);

        mockMvc.perform(get("/post/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("Post title"));
    }

    @Test
    public void getProducts() throws Exception {
        Post post1 = new Post();
        post1.setId(1L);
        post1.setTitle("Post title 1");
        post1.setBody("This is a post body. This is a post body. This is a post body.");

        Post post2 = new Post();
        post2.setId(1L);
        post2.setTitle("Post title 2");
        post2.setBody("This is a post body. This is a post body. This is a post body.");

        postDao.save(post1);
        postDao.save(post2);

//        when(postDao.findAll()).thenReturn(Collections.singletonList(post1));
//        when(postDao.findAll()).thenReturn(Collections.list(post1, post2));

        mockMvc.perform(get("/post/getAll")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].title").value("Post title 1"))
                .andExpect(jsonPath("$[1].title").value("Post title 2"));
    }

}
