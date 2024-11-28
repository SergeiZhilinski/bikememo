package com.sz.bikememoback.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sz.bikememoback.dtos.CreateNotationMessage;
import com.sz.bikememoback.dtos.NotationMessage;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Notation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullPicture;
    private String croppedPicture;


    private String title;
    private String description;
    private String dateOfCreation;
    private String dateOfUpdate;
    @OneToMany(mappedBy = "notation", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    List<Comment> comments = new ArrayList<>();

    public void addComment(Comment comment) {
        comment.setNotation(this);
        comments.add(comment);
    }

    public void removeComment(Comment comment) {
        comment.setNotation(null);
        comments.remove(comment);
    }
    public String toString() {
        return ("id: "+ id +", full pic: "+fullPicture + ", title "+title +", description "+description +", date "+dateOfCreation +", date "+dateOfUpdate);
    }

    public static Notation of(NotationMessage message) {
        return new Notation(
                message.id(),
                message.fullPicture(),
                message.croppedPicture(),
                message.title(),
                message.description(),
                message.dateOfCreation(),
                message.dateOfUpdate(),
                message.comments()
        );
    }
    public static Notation create(CreateNotationMessage message) {
        return new Notation(
                null,
                message.fullPicture(),
                message.croppedPicture(),
                message.title(),
                message.description(),
                message.dateOfCreation(),
                message.dateOfUpdate(),
                new ArrayList<>()
        );
    }

}

