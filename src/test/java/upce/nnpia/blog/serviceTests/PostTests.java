package upce.nnpia.blog.serviceTests;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestContextManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import upce.nnpia.blog.dataFactory.Creator;
import upce.nnpia.blog.dataFactory.PostTestDataFactory;
import upce.nnpia.blog.entity.Comment;
import upce.nnpia.blog.entity.Post;
import upce.nnpia.blog.entity.User;
import java.util.Set;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = {"upce.nnpia.blog.service.impl"})
@Import({Creator.class, TestContextManager.class, PasswordEncoder.class})
public class PostTests {

//    @Autowired
//    private PostTestDataFactory postTestDataFactory;

    @Autowired
    Creator creator;

    @Test
    public void addCommentToPost() {
        User user = (User) creator.saveEntity(new User());
//        User user = postTestDataFactory.
        Post post = (Post) creator.saveEntity(new Post());
        post.setUser(user);
        Comment comment1 = (Comment) creator.saveEntity(new Comment());
        Comment comment2 = (Comment) creator.saveEntity(new Comment());
        Comment comment3 = (Comment) creator.saveEntity(new Comment());
        Set<Comment> comments = Set.of(comment1, comment2, comment3);
        post.setComments(comments);

        Assertions.assertTrue(post.getComments().size() == 3);
    }
}
