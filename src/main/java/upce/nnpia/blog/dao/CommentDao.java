package upce.nnpia.blog.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import upce.nnpia.blog.entity.Comment;

import java.util.List;

public interface CommentDao extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId);
}
