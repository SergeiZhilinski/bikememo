package com.sz.bikememo.adapters;
import com.sz.bikememo.models.Comment;
import com.sz.bikememo.repos.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentRepository commentRepository;

    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment, @AuthenticationPrincipal Jwt jwt) {
        String userId = jwt.getSubject();
        comment.setUserId(userId);
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUpdatedAt(LocalDateTime.now());
        Comment savedComment = commentRepository.save(comment);
        return ResponseEntity.ok(savedComment);
    }

    @GetMapping("/by-record/{recordId}")
    public List<Comment> getCommentsByRecordId(@PathVariable String recordId) {
        return commentRepository.findByRecordId(recordId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable String id, @RequestBody Comment comment, @AuthenticationPrincipal Jwt jwt) {
        String userId = jwt.getSubject();
        return (ResponseEntity<Comment>) commentRepository.findById(id)
                .map(existingComment -> {
                    if (!existingComment.getUserId().equals(userId)) {
                        return ResponseEntity.status(403).build();
                    }
                    existingComment.setTitle(comment.getTitle());
                    existingComment.setBody(comment.getBody());
                    existingComment.setUpdatedAt(LocalDateTime.now());
                    commentRepository.save(existingComment);
                    return ResponseEntity.ok(existingComment);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteComment(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
        String userId = jwt.getSubject();
        return commentRepository.findById(id)
                .map(existingComment -> {
                    if (!existingComment.getUserId().equals(userId)) {
                        return ResponseEntity.status(403).build();
                    }
                    commentRepository.delete(existingComment);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
