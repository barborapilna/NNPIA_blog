package upce.nnpia.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import upce.nnpia.blog.dto.CommentDto;
import upce.nnpia.blog.entity.Comment;
import upce.nnpia.blog.entity.Post;
import upce.nnpia.blog.service.CommentService;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comment")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public void addComment(@RequestBody CommentDto comment) {
        commentService.save(comment);
    }

    @GetMapping("/comment/getAll")
    public List<Comment> getAllPosts() {
        return commentService.findAll();
    }

    @Transactional
    @DeleteMapping("/removeComment/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public void removeComment(@RequestBody Comment comment) {
        commentService.delete(comment.getId());
    }
}
