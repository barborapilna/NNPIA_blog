package upce.nnpia.blog.serviceTests;

import org.junit.Test;
import org.junit.runner.RunWith;
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


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = {"upce.nnpia.blog.service.impl"})
@Import({Creator.class, PostTestDataFactory.class})
public class PostTests {

    @Autowired
    private PostTestDataFactory postTestDataFactory;

    @Autowired
    Creator creator;

    @Test
    public void addCommentToPost() {
        User user = (User) creator.saveEntity(new User());
        Post post = (Post) creator.saveEntity(new Post());
        Comment comment1 = (Comment) creator.saveEntity(new Comment());
        Comment comment2 = (Comment) creator.saveEntity(new Comment());

        Post post1 = postTestDataFactory.saveNewPost1();

    }
}
