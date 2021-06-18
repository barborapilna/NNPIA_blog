package upce.nnpia.blog.service;

import upce.nnpia.blog.dto.PostDto;
import upce.nnpia.blog.entity.Post;
import java.util.List;
import java.util.Optional;

public interface PostService {

    void save(PostDto post);

    List<Post> findAll();

    void delete(long id);

    Post findOne(String title);

    Optional<Post> findById(long id);

    void update(PostDto postDto);
}
