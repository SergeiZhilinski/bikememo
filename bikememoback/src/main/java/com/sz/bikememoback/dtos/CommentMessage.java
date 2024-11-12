package com.sz.bikememoback.dtos;

import com.sz.bikememoback.models.Comment;

public record CommentMessage(Long id,
                             Long notationId,
                             int orderOfComment,
                             String commentTitle,
                             String commentText,
                             String dateOfCreation,
                             String dateOfUpdate) {
    public static CommentMessage of(Comment comment) {
        return new CommentMessage(
                comment.getId(),
                comment.getNotation().getId(),
                comment.getOrderOfComment(),
                comment.getCommentTitle(),
                comment.getCommentText(),
                comment.getDateOfCreation(),
                comment.getDateOfUpdate()
        );
    }
}
