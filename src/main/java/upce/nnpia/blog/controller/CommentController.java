package upce.nnpia.blog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upce.nnpia.blog.dto.CommentDto;
import upce.nnpia.blog.entity.Comment;
import upce.nnpia.blog.security.CurrentUser;
import upce.nnpia.blog.security.UserDetail;
import upce.nnpia.blog.service.CommentService;
import upce.nnpia.blog.service.Response;

import javax.transaction.Transactional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comment")
    public ResponseEntity<?> addComment(@CurrentUser UserDetail user, @RequestBody CommentDto comment) {
        try {
            return new ResponseEntity<>(commentService.save(user, comment), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Response("Add comment failed."), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/comment/{id}")
    public ResponseEntity<?> update(@RequestBody CommentDto commentDto) {
        try {
            commentService.update(commentDto);
            return new ResponseEntity<>(new Response("Your comment was edited."), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Response("Edit comment failed."), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/comment/{id}")
    public ResponseEntity<?> getComment(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(commentService.findById(id), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Response("Get comment failed."), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/comments/{postId}")
    public ResponseEntity<?> getPostComments(@PathVariable Long postId) {
        try {
            return new ResponseEntity<>(commentService.getPostComments(postId), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Response("Get comments failed."), HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    @DeleteMapping("/comment/{id}")
    public ResponseEntity<?> removeComment(@RequestBody Comment comment) {
        try {
            commentService.delete(comment.getId());
            return new ResponseEntity<>(new Response("Your comment was deleted."), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Response("Delete comment failed."), HttpStatus.BAD_REQUEST);
        }
    }
}
