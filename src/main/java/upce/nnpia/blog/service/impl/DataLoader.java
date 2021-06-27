package upce.nnpia.blog.service.impl;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import upce.nnpia.blog.dao.RoleDao;
import upce.nnpia.blog.dao.UserDao;
import upce.nnpia.blog.entity.Role;
import upce.nnpia.blog.entity.RoleType;
import upce.nnpia.blog.entity.User;

@Component
public class DataLoader implements ApplicationRunner {

    private final RoleDao roleDao;
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(RoleDao roleDao, UserDao userDao, PasswordEncoder passwordEncoder) {
        this.roleDao = roleDao;
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    public void run(ApplicationArguments args) {
        if (roleDao.findByRoleName(RoleType.ROLE_USER) == null)
            roleDao.saveAndFlush(new Role(RoleType.ROLE_USER));
        if (roleDao.findByRoleName(RoleType.ROLE_ADMIN) == null)
            roleDao.saveAndFlush(new Role(RoleType.ROLE_ADMIN));

        User user = new User("Pepa", "Novák", "pepanovak", passwordEncoder.encode("password"), roleDao.findByRoleName(RoleType.ROLE_USER));
        User admin = new User("Tomáš", "Steklý", "tomasstekly", passwordEncoder.encode("password"), roleDao.findByRoleName(RoleType.ROLE_ADMIN));

        if (!userDao.existsByUsername(user.getUsername()))
            userDao.saveAndFlush(user);
        if (!userDao.existsByUsername(admin.getUsername()))
            userDao.saveAndFlush(admin);
    }
}