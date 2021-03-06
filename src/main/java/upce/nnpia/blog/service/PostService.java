package upce.nnpia.blog.service;

import upce.nnpia.blog.dto.PostDto;
import upce.nnpia.blog.dto.PostGetDto;
import upce.nnpia.blog.security.UserDetail;

import java.util.List;

public interface PostService {

    void save(UserDetail user, PostDto post);

    List<PostGetDto> findAll();

    void delete(Long id);

    PostGetDto findById(Long id);

    void update(PostDto postDto);
}
