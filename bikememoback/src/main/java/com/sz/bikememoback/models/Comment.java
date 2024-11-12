package com.sz.bikememoback.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sz.bikememoback.dtos.CommentMessage;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "notation_id")
    @JsonBackReference
    private Notation notation;

    private int orderOfComment;
    private String commentTitle;
    private String commentText;
    private String dateOfCreation;
    private String dateOfUpdate;

    public String toString() {
        return ("id: "+ id +", notification: "+notation.getId() + ", title "+ commentTitle +", description "+commentText +", date "+dateOfCreation +", date "+dateOfUpdate);
    }

    public static Comment of(CommentMessage message) {
        Notation notation = new Notation();
        notation.setId(message.notationId());
        return new Comment(
                message.id(),
                notation,
                message.orderOfComment(),
                message.commentTitle(),
                message.commentText(),
                message.dateOfCreation(),
                message.dateOfUpdate()
        );
    }
}
