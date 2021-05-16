package upce.nnpia.blog.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import upce.nnpia.blog.entity.User;

public interface UserDao extends JpaRepository<User, Long>{
    boolean existsByUsername(String username);
    User findByUsername(String username);
}