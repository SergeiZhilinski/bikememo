package com.sz.bikememoback.services;

import com.sz.bikememoback.models.Notation;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
class NotationServiceTest {
    @Autowired
    private NotationService notationService;

    @Test
    void getAllNotations() {
        System.out.println(notationService.getAllNotations());
    }

    @Test
    void getNotationById() {
        Notation notation = new Notation();
        notation.setTitle("My First Notation");
        notation.setDescription("My Description");
        Long savedNotationId = notationService.createNotation(notation).getId();
        Notation readedNotation = notationService.getNotationById(savedNotationId);
        System.out.println(readedNotation.toString());

    }

    @Test
    void createNotation() {
        Notation notation = new Notation();
        notation.setTitle("My First Notation");
        notation.setDescription("My Description");
        notationService.createNotation(notation);
        System.out.println(notationService.getAllNotations());
    }

    @SneakyThrows
    @Test
    void updateNotation() {

        Notation notation = new Notation();
        notation.setTitle("My First Notation");
        notation.setDescription("My Description");
        Long savedNotationId = notationService.createNotation(notation).getId();
        Notation readedNotation = notationService.getNotationById(savedNotationId);
        System.out.println(readedNotation.toString());
        readedNotation.setTitle("My Second Notation");
        Thread.sleep(5000);
        Notation updatedNotation = notationService.updateNotation(readedNotation);
        System.out.println(updatedNotation.toString());


    }

    @Test
    void deleteNotation() {
        Notation notation = new Notation();
        notation.setTitle("My First Notation");
        notation.setDescription("My Description");
        Long savedNotationId = notationService.createNotation(notation).getId();
        notationService.deleteNotation(savedNotationId);
        Notation readedNotation = notationService.getNotationById(savedNotationId);
        System.out.println(readedNotation.toString());

    }
}