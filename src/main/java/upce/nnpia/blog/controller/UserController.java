package upce.nnpia.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import upce.nnpia.blog.dao.UserDao;
import upce.nnpia.blog.dto.UserDto;
import upce.nnpia.blog.entity.ApiResponse;
import upce.nnpia.blog.entity.User;
import upce.nnpia.blog.service.UserService;


import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    private UserService userService;

    @PostMapping
    public ApiResponse<User> saveUser(@RequestBody UserDto user) {
        return new ApiResponse<>(HttpStatus.OK.value(), "User successfully saved.", userService.save(user));
    }

    @GetMapping
    public ApiResponse<List<User>> listUser() {
        return new ApiResponse<>(HttpStatus.OK.value(), "User successfully list fetched.", userService.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<User> getOne(@PathVariable int id) {
        return new ApiResponse<>(HttpStatus.OK.value(), "User successfully fetched.", userService.findById(id));
    }

    @PutMapping("/{id}")
    public ApiResponse<UserDto> update(@RequestBody UserDto userDto) {
        return new ApiResponse<>(HttpStatus.OK.value(), "User successfully updated.", userService.update(userDto));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable int id) {
        userService.delete(id);
        return new ApiResponse<>(HttpStatus.OK.value(), "User successfully deleted.", null);
    }

    @PostMapping("/registration")
    public ApiResponse<Void> registrationUser(@RequestBody UserDto user) {

        boolean existsByUsername = userDao.existsByUsername(user.getUsername());
        user.setPassword(user.getPassword());

        if (existsByUsername) {
            System.out.println("User already exist!");
            return new ApiResponse<>(HttpStatus.OK.value(), "This username is already used!", null);
        } else { //zapise do DB
            System.out.println("Registration: " + user.getFirstName());
            return new ApiResponse<>(HttpStatus.OK.value(), "User successfully saved.", userService.save(user));
        }
    }

}