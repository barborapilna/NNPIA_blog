package upce.nnpia.blog.service;

import upce.nnpia.blog.dto.CommentDto;
import upce.nnpia.blog.dto.CommentGetDto;
import upce.nnpia.blog.entity.Comment;
import upce.nnpia.blog.security.UserDetail;

import java.util.List;

public interface CommentService {

    void save(UserDetail user, CommentDto comment);

    void update(CommentDto comment);

    void delete(long id);

    List<Comment> findAll();

    CommentGetDto findById(long id);

    List<CommentGetDto> getPostComments(long postId);
}
