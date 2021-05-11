package upce.nnpia.blog.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import upce.nnpia.blog.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentDao extends JpaRepository<Comment, Long> {
    Optional<List<Comment>> findByPostId(Long postId);
}
