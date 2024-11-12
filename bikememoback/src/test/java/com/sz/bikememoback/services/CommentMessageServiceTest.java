package com.sz.bikememoback.services;

import com.sz.bikememoback.models.Comment;
import com.sz.bikememoback.models.Notation;
import com.sz.bikememoback.persistance.CommentRepository;
import com.sz.bikememoback.persistance.NotationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class CommentMessageServiceTest {
    @Autowired
    private  CommentService commentService;
    @Autowired
    private NotationService notationService;

    private Notation notation01,notation02;
    private Comment comment01,comment02, comment03;
    @BeforeEach
    void setUp() {
        notation01 = new Notation();
        notation02 = new Notation();

        comment01 = new Comment();
        comment02 = new Comment();
        comment03 = new Comment();

        notation01.setTitle("My First Notation");
        notation01.setDescription("My Description 01");
        notation02.setTitle("My Second Notation");
        notation02.setDescription("My Description 02");

        comment01.setCommentTitle("My First Comment");
        comment01.setCommentText("My First Comment description");
        comment02.setCommentTitle("My Second Comment");
        comment02.setCommentText("My Second Comment description");
        comment03.setCommentTitle("My Third Comment");
        comment03.setCommentText("My Third Comment description");

    }

    @Test
    void getCommentsByNotationId() {
        notationService.createNotation(notation01);
        notationService.createNotation(notation02);
        comment01.setNotation(notation01);
        comment02.setNotation(notation02);
        comment03.setNotation(notation02);

        commentService.createComment(comment01);
        commentService.createComment(comment02);
        commentService.createComment(comment03);
        System.out.println(commentService.getCommentsByNotationId(notation01.getId()));
        System.out.println(commentService.getCommentsByNotationId(notation02.getId()));

    }

    @Test
    void getCommentById() {
    }

    @Test
    void createComment() {
    }

    @Test
    void updateComment() {
    }

    @Test
    void deleteComment() {
    }
}