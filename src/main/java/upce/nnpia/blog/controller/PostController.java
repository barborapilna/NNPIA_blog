package upce.nnpia.blog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upce.nnpia.blog.dto.PostDto;
import upce.nnpia.blog.dto.PostGetDto;
import upce.nnpia.blog.entity.Post;
import upce.nnpia.blog.security.CurrentUser;
import upce.nnpia.blog.security.UserDetail;
import upce.nnpia.blog.service.PostService;
import upce.nnpia.blog.service.Response;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/post")
    public ResponseEntity<?> addPost(@CurrentUser UserDetail user, @RequestBody PostDto post) {
        try {
            postService.save(user, post);
            return new ResponseEntity<>(new Response("Your post was added."), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Response("Adding post failed."), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/post/getAll")
    public List<PostGetDto> getAllPosts() {
        return postService.findAll();
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<?> getPost(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(postService.findById(id), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Response("Get post failed."), HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/post/{id}")
    public ResponseEntity<?> update(@RequestBody PostDto postDto) {
        try {
            postService.update(postDto);
            return new ResponseEntity<>(new Response("Your post was edited."), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Response("Edit post failed."), HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    @DeleteMapping("/post/{id}")
    public ResponseEntity<?> removePost(@RequestBody Post post) {
        try {
            postService.delete(post.getId());
            return new ResponseEntity<>(new Response("Post was deleted."), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Response("Delete post failed."), HttpStatus.BAD_REQUEST);
        }

    }
}
