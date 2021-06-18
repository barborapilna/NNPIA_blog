package upce.nnpia.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import upce.nnpia.blog.dto.PostDto;
import upce.nnpia.blog.entity.Post;
import upce.nnpia.blog.service.PostService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/post")
    public void addPost(@RequestBody PostDto post) {
        postService.save(post);
    }

    @GetMapping("/post/getAll")
    public List<Post> getAllPosts() {
        return postService.findAll();
    }

    @GetMapping("/post/{id}")
    public Optional<Post> getPost(@PathVariable int id) {
        return postService.findById(id);
    }

    @PutMapping("/post/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<Void> update(@RequestBody PostDto postDto) {
        postService.update(postDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/post/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public void removePost(@RequestBody Post post) {
        postService.delete(post.getId());
    }
}
