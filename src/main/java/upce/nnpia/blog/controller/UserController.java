package upce.nnpia.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import upce.nnpia.blog.dao.UserDao;
import upce.nnpia.blog.dto.UserDto;
import upce.nnpia.blog.entity.User;
import upce.nnpia.blog.service.UserService;


import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    private final UserDao userDao;
    private final UserService userService;

    @Autowired
    public UserController(UserDao userDao, UserService userService) {
        this.userDao = userDao;
        this.userService = userService;
    }

    @PostMapping("/user")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<User> saveUser(@RequestBody UserDto user) {
        return new ResponseEntity<>( userService.save(user), HttpStatus.OK);
    }

    @GetMapping("/user/getAll")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<List<User>> listUser() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<User> getUser(@PathVariable int id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/user/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<UserDto> update(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.update(userDto), HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        userService.delete(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("/registration")
//    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<String> registrationUser(@RequestBody UserDto user) {

        boolean existsByUsername = userDao.existsByUsername(user.getUsername());
        user.setPassword(user.getPassword());

        if (existsByUsername) {
            return ResponseEntity.badRequest().body("User already exist!");
        } else { //zapise do DB
            userService.save(user);
            return ResponseEntity.ok().body("Registration: " + user.getFirstName());
        }
    }

}