package upce.nnpia.blog.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import upce.nnpia.blog.entity.Post;

import java.util.List;

public interface PostDao extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p WHERE p.title = :title")
    Post findByTitle(String title);

    List<Post> findAll();
//    Page<Post> findAll(Pageable pageable);
    void deleteById(Long id);
}
