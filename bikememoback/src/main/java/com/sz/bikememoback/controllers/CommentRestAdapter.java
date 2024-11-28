package com.sz.bikememoback.controllers;

import com.sz.bikememoback.dtos.comment.RequestCommentMessage;
import com.sz.bikememoback.dtos.comment.ResponseCommentMessage;
import com.sz.bikememoback.dtos.comment.CreateCommentMessage;
import com.sz.bikememoback.models.Comment;
import com.sz.bikememoback.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("rest/v1/comment")
public class CommentRestAdapter {
    private final CommentService commentService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<ResponseCommentMessage> getAllCommentsToNotation(@RequestParam Long id) {
        return commentService.getCommentsByNotationId(id).stream().map(ResponseCommentMessage::of).toList();
    }

    @PutMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseCommentMessage createComment(@RequestBody CreateCommentMessage commentMessage) {
        return ResponseCommentMessage.of(commentService.createComment( Comment.create(commentMessage)));
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseCommentMessage aditComment(@RequestBody RequestCommentMessage responseCommentMessage) {
        return ResponseCommentMessage.of(commentService.createComment( Comment.of(responseCommentMessage)));
    }

    @DeleteMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public void deleteComment(@RequestParam Long commentId, @RequestParam Long notationId) {
        commentService.deleteComment(notationId, commentId);
    }

}
