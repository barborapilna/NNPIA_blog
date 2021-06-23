package upce.nnpia.blog.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import upce.nnpia.blog.dao.CommentDao;
import upce.nnpia.blog.dao.PostDao;
import upce.nnpia.blog.dao.UserDao;
import upce.nnpia.blog.dto.CommentDto;
import upce.nnpia.blog.dto.CommentGetDto;
import upce.nnpia.blog.entity.Comment;
import upce.nnpia.blog.security.UserDetail;
import upce.nnpia.blog.service.CommentService;

import java.util.ArrayList;
import java.util.List;

@Service(value = "commentService")
public class CommentServiceImpl implements CommentService {

    private CommentDao commentDao;
    private PostDao postDao;
    private UserDao userDao;

    public CommentServiceImpl(CommentDao commentDao, PostDao postDao, UserDao userDao) {
        this.commentDao = commentDao;
        this.postDao = postDao;
        this.userDao = userDao;
    }

    @Override
    public CommentGetDto save(UserDetail user, CommentDto comment) {
        Comment newComment = new Comment();
        newComment.setPost(postDao.findById(comment.getPostId()).orElseThrow());
        newComment.setBody(comment.getBody());
        newComment.setUser(userDao.findByUsername(user.getUsername()));
        commentDao.saveAndFlush(newComment);

        var commentGetDto = new CommentGetDto();

        commentGetDto.setId(newComment.getId());
        commentGetDto.setBody(newComment.getBody());
        commentGetDto.setUserName(newComment.getUser().getUsername());

        return commentGetDto;
    }

    @Override
    public void update(CommentDto comment) {
        Comment newComment = commentDao.findById(comment.getId()).orElseThrow();
        BeanUtils.copyProperties(comment, newComment, "post_id");
        commentDao.save(newComment);
    }

    @Override
    public void delete(Long id) {
        commentDao.findById(id).ifPresent(p -> {
            commentDao.delete(p);
        });
    }

    @Override
    public CommentGetDto findById(Long id) {
        var commentGetDto = new CommentGetDto();
        var comment = commentDao.findById(id);

        commentGetDto.setId(comment.orElseThrow().getId());
        commentGetDto.setBody(comment.get().getBody());
        commentGetDto.setUserName(comment.get().getUser().getUsername());

        return commentGetDto;
    }

    @Override
    public List<Comment> findAll() {
        return commentDao.findAll();
    }

    @Override
    public List<CommentGetDto> getPostComments(Long postId) {
        var commentsGetDto = new ArrayList<CommentGetDto>();
        var comments = commentDao.findByPostId(postId);

        for (var comment : comments) {
            commentsGetDto.add(new CommentGetDto(comment.getId(), comment.getBody(), comment.getUser().getUsername()));
        }

        return commentsGetDto;
    }
}
