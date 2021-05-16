package upce.nnpia.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import upce.nnpia.blog.dto.CommentDto;
import upce.nnpia.blog.entity.Comment;
import upce.nnpia.blog.service.CommentService;

import javax.transaction.Transactional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/addComment")
    public void addComment(@RequestBody CommentDto comment) {
        commentService.save(comment);
    }

    @Transactional
    @PostMapping("/removeComment/{id}")
    public void removeComment(@RequestBody Comment comment) {
        commentService.delete(comment.getId());
    }
}