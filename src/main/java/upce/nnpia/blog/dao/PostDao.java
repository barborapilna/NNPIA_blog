package upce.nnpia.blog.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import upce.nnpia.blog.entity.Post;

import java.util.List;

public interface PostDao extends JpaRepository<Post, Long> {
    boolean existsByTitle(String title);
    Post findByTitle(String title);

    List<Post> findAll();
    void delete(Post post);
}
