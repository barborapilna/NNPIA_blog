package upce.nnpia.blog.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import upce.nnpia.blog.dao.UserDao;
import upce.nnpia.blog.dto.UserDto;
import upce.nnpia.blog.entity.User;
import upce.nnpia.blog.security.UserDetail;
import upce.nnpia.blog.service.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void delete(Long id) {
        userDao.deleteById(id);
    }

    @Override
    public User findOne(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User findById(Long id) {
        Optional<User> optionalUser = userDao.findById(id);
        return optionalUser.orElse(null);
    }

    @Override
    public UserDto update(UserDto userDto) {
        User user = userDao.findByUsername(userDto.getUsername());
        if (user != null) {
            BeanUtils.copyProperties(userDto, user, "password", "username");
            userDao.save(user);
        }
        return userDto;
    }

    @Override
    public void save(User user) {
        boolean existsByUsername = userDao.existsByUsername(user.getUsername());
        if (!existsByUsername) {
            userDao.save(user);
        } else {
            throw new UsernameNotFoundException("This username already exist.");
        }
    }

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        userDao.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    private List<SimpleGrantedAuthority> getAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public UserDetail loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getRoleName().name()));

        return new UserDetail(user.getUsername(), user.getPassword(), authorities);
    }
}
