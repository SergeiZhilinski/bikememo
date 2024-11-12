package com.sz.bikememoback.controllers;

import com.sz.bikememoback.dtos.CommentMessage;
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
    public List<CommentMessage> getAllCommentsToNotation(@RequestParam Long id) {
        return commentService.getCommentsByNotationId(id).stream().map(CommentMessage::of).toList();
    }

    @PutMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentMessage createComment(@RequestBody CommentMessage commentMessage) {
        return CommentMessage.of(commentService.createComment( Comment.of(commentMessage)));
    }

    //TODO update, delete


}
