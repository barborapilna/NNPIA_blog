package upce.nnpia.blog.service;

import upce.nnpia.blog.dto.CommentDto;

public interface CommentService {

    void save(CommentDto comment);

    void update(CommentDto comment);

    void delete(long id);

}
