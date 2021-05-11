package upce.nnpia.blog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import upce.nnpia.blog.dto.PostDto;
import upce.nnpia.blog.entity.Post;

import java.util.Optional;

public interface PostService {

    void save(PostDto post);

    Page<Post> findAll(Pageable pageable);

    void delete(long id);

    Post findOne(String title);

    Optional<Post> findById(long id);

    void update(PostDto postDto);
}
