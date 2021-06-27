package upce.nnpia.blog.dataTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import upce.nnpia.blog.dao.CommentDao;
import upce.nnpia.blog.dataFactory.CommentTestDataFactory;
import upce.nnpia.blog.entity.Comment;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(CommentTestDataFactory.class)
class CommentTests {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private CommentTestDataFactory commentTestDataFactory;

    @Test
    void saveOneComment() {
        commentTestDataFactory.saveNewComment1();
        List<Comment> all = commentDao.findAll();
        Assertions.assertEquals(all.size(), 1);
    }

    @Test
    void saveComments() {
        commentTestDataFactory.saveNewComment1();
        commentTestDataFactory.saveNewComment2();
        List<Comment> all = commentDao.findAll();
        Assertions.assertEquals(all.size(), 2);
    }

    @Test
    void getCommentByPostID() {
        Comment comment = commentTestDataFactory.saveNewComment1();
        commentTestDataFactory.saveNewComment2();
        List<Comment> all = commentDao.findByPostId(comment.getPost().getId());
        Assertions.assertEquals(all.size(), 2);
    }

}
