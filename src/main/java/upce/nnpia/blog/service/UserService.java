package upce.nnpia.blog.service;

import upce.nnpia.blog.dto.UserDto;
import upce.nnpia.blog.entity.User;
import java.util.List;

public interface UserService {

    void save(User user);

    List<User> findAll();

    void delete(Long id);

    User findOne(String username);

    User findById(Long id);

    UserDto update(UserDto userDto);
}
