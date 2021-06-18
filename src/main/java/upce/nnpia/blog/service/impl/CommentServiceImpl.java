package upce.nnpia.blog.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upce.nnpia.blog.dao.CommentDao;
import upce.nnpia.blog.dao.PostDao;
import upce.nnpia.blog.dao.UserDao;
import upce.nnpia.blog.dto.CommentDto;
import upce.nnpia.blog.entity.Comment;
import upce.nnpia.blog.service.CommentService;
import java.util.List;

@Service(value = "commentService")
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private PostDao postDao;

    @Autowired
    private UserDao userDao;

    @Override
    public void save(CommentDto comment) {
        Comment newComment = new Comment();
        newComment.setPost(postDao.findById(comment.getPostId()).get());
        newComment.setBody(comment.getBody());
        newComment.setUser(userDao.findById(comment.getUserId()).get());
        commentDao.saveAndFlush(newComment);
    }

    @Override
    public void update(CommentDto comment) {
        Comment newComment = commentDao.findById(comment.getId()).get();
        BeanUtils.copyProperties(comment, newComment, "post_id");
        commentDao.save(newComment);
    }

    @Override
    public void delete(long id) {
        commentDao.findById(id).ifPresent(p -> {
            commentDao.delete(p);
        });
    }

    @Override
    public List<Comment> findAll() {
        return commentDao.findAll();
    }
}
