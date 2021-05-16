package upce.nnpia.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import upce.nnpia.blog.dto.PostDto;
import upce.nnpia.blog.entity.Post;
import upce.nnpia.blog.service.PostService;

import javax.transaction.Transactional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/addPost")
    public void addPost(@RequestBody PostDto post) {
        postService.save(post);
    }

    @GetMapping("/getAllPosts")
    public Page<Post> getAllPosts(Pageable pageable) {
        return postService.findAll(pageable);
    }

    @Transactional
    @PostMapping("/removePost/{id}")
    public void removePost(@RequestBody Post post) {
        postService.delete(post.getId());
    }
}
