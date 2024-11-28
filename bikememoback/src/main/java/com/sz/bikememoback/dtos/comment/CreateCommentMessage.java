package com.sz.bikememoback.dtos.comment;

import jakarta.validation.constraints.NotNull;

public record CreateCommentMessage(@NotNull Long notationId,
                                   int orderOfComment,
                                   String commentTitle,
                                   String commentText,
                                   String dateOfCreation,
                                   String dateOfUpdate) {

}
