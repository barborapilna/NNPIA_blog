package upce.nnpia.blog.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import upce.nnpia.blog.entity.Comment;

import java.util.List;

@Repository
public interface CommentDao extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId);
}
