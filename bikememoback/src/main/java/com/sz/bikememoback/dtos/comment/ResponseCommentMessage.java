package com.sz.bikememoback.dtos.comment;

import com.sz.bikememoback.models.Comment;

public record ResponseCommentMessage(Long id,
                                     Long notationId,
                                     int orderOfComment,
                                     String commentTitle,
                                     String commentText,
                                     String dateOfCreation,
                                     String dateOfUpdate) {
    public static ResponseCommentMessage of(Comment comment) {
        return new ResponseCommentMessage(
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
