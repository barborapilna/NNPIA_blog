package upce.nnpia.blog.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import upce.nnpia.blog.entity.Role;
import upce.nnpia.blog.entity.RoleType;

import java.util.List;

public interface RoleDao extends JpaRepository<Role, Long> {
    Role findByRoleName(RoleType roleName);
    List<Role> findAll();
    void delete(Role post);
    boolean existsByRoleName(RoleType roleType);
}

