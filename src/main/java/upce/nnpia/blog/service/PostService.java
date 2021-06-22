package upce.nnpia.blog.service;

import org.springframework.security.core.userdetails.UserDetails;
import upce.nnpia.blog.dto.PostDto;
import upce.nnpia.blog.dto.PostGetDto;
import upce.nnpia.blog.entity.Post;
import upce.nnpia.blog.security.UserDetail;

import java.util.List;

public interface PostService {

    void save(UserDetail user, PostDto post);

    List<Post> findAll();

    void delete(long id);

    PostGetDto findOne(String title);

    PostGetDto findById(long id);

    void update(PostDto postDto);
}
