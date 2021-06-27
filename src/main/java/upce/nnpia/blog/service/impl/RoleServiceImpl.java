package upce.nnpia.blog.service.impl;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import upce.nnpia.blog.dao.RoleDao;
import upce.nnpia.blog.entity.Role;
import upce.nnpia.blog.entity.RoleType;
import upce.nnpia.blog.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }


    @Override
    public void save(Role role) {
        boolean exist = roleDao.existsByRoleName(role.getRoleName());
        if(exist)
        {
            roleDao.save(role);
        } else {
            throw new UsernameNotFoundException("This role name already exist.");
        }
    }

    @Override
    public Role findByRoleName(RoleType roleType) {
        Role found = roleDao.findByRoleName(roleType);
        if(found != null)
        {
            return found;
        } else {
            throw new UsernameNotFoundException("This role name already exist.");
        }
    }
}
