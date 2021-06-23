package upce.nnpia.blog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upce.nnpia.blog.dao.UserDao;
import upce.nnpia.blog.dto.UserDto;
import upce.nnpia.blog.service.Response;
import upce.nnpia.blog.service.UserService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    private final UserDao userDao;
    private final UserService userService;

    public UserController(UserDao userDao, UserService userService) {
        this.userDao = userDao;
        this.userService = userService;
    }

    @PostMapping("/user")
    public ResponseEntity<?> saveUser(@RequestBody UserDto user) {
        try {
            userService.save(user);
            return new ResponseEntity<>(new Response("User was added."), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Response("Add user failed."), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user/getAll")
    public ResponseEntity<?> listUser() {
        try {
            return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Response("Get users failed."), HttpStatus.BAD_REQUEST);
        }
    }

//    @GetMapping("/user/{id}")
//    public ResponseEntity<?> getUser(@PathVariable int id) {
//        try {
//            return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
//        } catch (Exception ex) {
//            return new ResponseEntity<>(new Response("Get user failed."), HttpStatus.BAD_REQUEST);
//        }
//    }

    @GetMapping("/user/{username}")
    public ResponseEntity<?> getUser(@PathVariable String username) {
        try {
            return new ResponseEntity<>(userService.findOne(username), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Response("Get user failed."), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<?> update(@RequestBody UserDto userDto) {
        try {
            userService.update(userDto);
            return new ResponseEntity<>(new Response("User was edited."), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Response("Edit user failed."), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            userService.delete(id);
            return new ResponseEntity<>(new Response("User was deleted."), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Response("Delete user failed."), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registrationUser(@RequestBody UserDto user) {
        try {
            userService.save(user);
            return new ResponseEntity<>(new Response("User was added."), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Response("Add user failed."), HttpStatus.BAD_REQUEST);
        }
    }

}