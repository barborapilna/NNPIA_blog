package upce.nnpia.blog.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import upce.nnpia.blog.entity.Post;

import java.util.List;

@Repository
public interface PostDao extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p WHERE p.title = :title")
    Post findByTitle(String title);

    List<Post> findAll();

    void deleteById(Long id);
}
