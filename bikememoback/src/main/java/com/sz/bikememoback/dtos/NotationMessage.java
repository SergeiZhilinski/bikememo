package com.sz.bikememoback.dtos;

import com.sz.bikememoback.models.Comment;
import com.sz.bikememoback.models.Notation;

import java.util.List;

public record NotationMessage(Long id, String fullPicture, String croppedPicture, String title, String description,
                              String dateOfCreation, String dateOfUpdate, List<Comment> comments) {
    public static NotationMessage of(Notation notation) {
        return new NotationMessage(
                notation.getId(),
                notation.getFullPicture(),
                notation.getCroppedPicture(),
                notation.getTitle(),
                notation.getDescription(),
                notation.getDateOfCreation(),
                notation.getDateOfUpdate(),
                notation.getComments());
    }
}
