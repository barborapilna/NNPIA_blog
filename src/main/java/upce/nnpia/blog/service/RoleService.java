package upce.nnpia.blog.service;

import upce.nnpia.blog.entity.Role;
import upce.nnpia.blog.entity.RoleType;

public interface RoleService {

    void save(Role role);
    Role findByRoleName(RoleType roleType);
}
