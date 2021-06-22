package upce.nnpia.blog.dataTests;

import upce.nnpia.blog.dao.PostDao;
import upce.nnpia.blog.dataFactory.PostTestDataFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import upce.nnpia.blog.entity.Post;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PostTestDataFactory.class)
class PostTests {

    @Autowired
    private PostDao postDao;

    @Autowired
    private PostTestDataFactory postTestDataFactory;

    @Test
    void saveOnePost() {
        List<Post> forRes = postDao.findAll();
        int size = forRes.size()+1;
        postTestDataFactory.saveNewPost1();
        List<Post> all = postDao.findAll();
        Assertions.assertEquals(all.size(), size);
    }

    @Test
    void savePosts() {
        List<Post> forRes = postDao.findAll();
        int size = forRes.size()+2;
        postTestDataFactory.saveNewPost1();
        postTestDataFactory.saveNewPost2();
        List<Post> all = postDao.findAll();
        Assertions.assertEquals(all.size(), size);
    }

    @Test
    void getPostByTitle() {
        postTestDataFactory.saveNewPost1();
        postTestDataFactory.saveNewPost2();
        Post result = postDao.findByTitle("This is a post title");
        Assertions.assertEquals("Another post body. Another post body. Another post body.", result.getBody());
    }

}
