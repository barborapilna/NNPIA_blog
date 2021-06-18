package upce.nnpia.blog.service;

import upce.nnpia.blog.dto.CommentDto;
import upce.nnpia.blog.entity.Comment;
import java.util.List;

public interface CommentService {

    void save(CommentDto comment);

    void update(CommentDto comment);

    void delete(long id);

    List<Comment> findAll();

}
