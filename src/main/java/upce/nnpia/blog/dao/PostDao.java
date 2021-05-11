package upce.nnpia.blog.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import upce.nnpia.blog.entity.Post;

public interface PostDao extends JpaRepository<Post, Long> {
    boolean existsByTitle(String title);
    Post findByTitle(String title);
    Page<Post> findAll(Pageable pageable);
    void delete(Post post);
}
