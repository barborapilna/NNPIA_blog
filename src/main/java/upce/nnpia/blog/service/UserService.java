package upce.nnpia.blog.service;

import upce.nnpia.blog.dto.UserDto;
import upce.nnpia.blog.entity.User;

import java.util.List;

public interface UserService {

    User save(UserDto user);

    List<User> findAll();

    void delete(long id);

    User findOne(String username);

    User findById(long id);

    UserDto update(UserDto userDto);
}
