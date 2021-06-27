package upce.nnpia.blog.dataFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import upce.nnpia.blog.dao.CommentDao;
import upce.nnpia.blog.entity.Comment;
import upce.nnpia.blog.entity.Post;

@Component
public class CommentTestDataFactory {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private PostTestDataFactory postTestDataFactory;

    public Comment saveNewComment1() {
        Post post = postTestDataFactory.saveNewPost1();

        Comment comment = new Comment();
        comment.setPost(post);
        comment.setBody("This is super truper first comment for this awesome post.");
        commentDao.save(comment);
        return comment;
    }

    public Comment saveNewComment2() {
        Post post = postTestDataFactory.saveNewPost1();

        Comment comment = new Comment();
        comment.setPost(post);
        comment.setBody("This is super truper second comment for that awesome post.");
        commentDao.save(comment);
        return comment;
    }
}
