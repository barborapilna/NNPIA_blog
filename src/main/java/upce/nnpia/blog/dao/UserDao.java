package upce.nnpia.blog.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import upce.nnpia.blog.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Long>{
    boolean existsByUsername(String username);
    User findByUsername(String username);
    void deleteById(Long id);
}
