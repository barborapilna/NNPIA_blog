package upce.nnpia.blog.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import upce.nnpia.blog.dto.UserDto;
import upce.nnpia.blog.entity.RoleType;
import upce.nnpia.blog.entity.User;
import upce.nnpia.blog.service.RegistrationService;
import upce.nnpia.blog.service.RoleService;
import upce.nnpia.blog.service.UserService;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final RoleService roleService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public RegistrationServiceImpl(RoleService roleService, UserService userService, PasswordEncoder passwordEncoder) {
        this.roleService = roleService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registration(UserDto user) {
        User temp = new User();
        temp.setFirstName(user.getFirstName());
        temp.setLastName(user.getLastName());
        temp.setUsername(user.getUsername());
        temp.setPassword(passwordEncoder.encode(user.getPassword()));
        temp.setRole(roleService.findByRoleName(RoleType.ROLE_USER));
        userService.save(temp);
    }
}
