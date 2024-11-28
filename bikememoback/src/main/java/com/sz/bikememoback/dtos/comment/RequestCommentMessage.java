package com.sz.bikememoback.dtos.comment;

public record RequestCommentMessage(Long id,
                                     Long notationId,
                                     int orderOfComment,
                                     String commentTitle,
                                     String commentText,
                                     String dateOfCreation,
                                     String dateOfUpdate) {

}