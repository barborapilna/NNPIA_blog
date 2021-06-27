package upce.nnpia.blog.serviceTests;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import upce.nnpia.blog.BlogApplication;
import upce.nnpia.blog.dao.RoleDao;
import upce.nnpia.blog.dto.UserDto;
import upce.nnpia.blog.entity.Role;
import upce.nnpia.blog.entity.RoleType;
import upce.nnpia.blog.entity.User;
import upce.nnpia.blog.service.RegistrationService;
import upce.nnpia.blog.service.RoleService;
import upce.nnpia.blog.service.UserService;
import upce.nnpia.blog.service.impl.RegistrationServiceImpl;
import upce.nnpia.blog.service.impl.RoleServiceImpl;

@SpringBootTest(classes = BlogApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({RegistrationService.class, UserService.class})
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



//        User newUser = new User();
//        newUser.setFirstName(user.getFirstName());
//        newUser.setLastName(user.getLastName());
//        newUser.setUsername(user.getUsername());
//        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
//        newUser.setRole(roleService.findByRoleName(RoleType.ROLE_USER));
//        userService.save(newUser);


//        User user = (User) creator.saveEntity(new User());
//        Post post = (Post) creator.saveEntity(new Post());
//        post.setUser(user);
////        Comment comment1 = (Comment) creator.saveEntity(new Comment());
////        Comment comment2 = (Comment) creator.saveEntity(new Comment());
////        Comment comment3 = (Comment) creator.saveEntity(new Comment());
////        Set<Comment> comments = Set.of(comment1, comment2, comment3);
////        post.setComments(comments);
//
//        Assertions.assertTrue(post.getComments().size() == 3);
    }
}
