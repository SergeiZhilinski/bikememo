package com.sz.bikememoback.dtos;

import com.sz.bikememoback.models.Comment;
import com.sz.bikememoback.models.Notation;

import java.util.List;

public record CreateNotationMessage(String fullPicture, String croppedPicture, String title, String description,
                                    String dateOfCreation, String dateOfUpdate) {
}
