package upce.nnpia.blog.serviceTests;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import upce.nnpia.blog.BlogApplication;
import upce.nnpia.blog.dto.UserDto;
import upce.nnpia.blog.entity.User;
import upce.nnpia.blog.service.RegistrationService;
import upce.nnpia.blog.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BlogApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RegistrationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private RegistrationService registrationService;

    @Test
    public void registerUser() {
        UserDto newUser = new UserDto();
        newUser.setFirstName("test");
        newUser.setLastName("testTest");
        newUser.setUsername("testUser");
        newUser.setPassword("testPasswd");

        registrationService.registration(newUser);
        User result = userService.findOne("testUser");
        Assertions.assertEquals(newUser.getUsername(), result.getUsername());
    }
}
