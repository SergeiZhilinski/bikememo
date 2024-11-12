package com.sz.bikememoback.services;

import com.sz.bikememoback.exceptions.NotationNotFoundException;
import com.sz.bikememoback.models.Notation;
import com.sz.bikememoback.persistance.NotationRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotationService {
    private final NotationRepository notationRepository;

    @Autowired
    public NotationService(NotationRepository notationRepository) {
        this.notationRepository = notationRepository;
    }

    public List<Notation> getAllNotations() {
        return notationRepository.findAll();
    }

    @SneakyThrows
    public Notation getNotationById(Long id) {
        return notationRepository.findById(id).orElseThrow(() -> new NotationNotFoundException(id));
    }

    public Notation createNotation(Notation notation) {
        notation.setDateOfCreation(LocalDateTime.now().toString());
        notation.setDateOfUpdate(LocalDateTime.now().toString());
        return notationRepository.save(notation);
    }

    public Notation updateNotation(Notation updatedNotation) {
        return notationRepository.findById(updatedNotation.getId()).map(existingNotation -> {
            existingNotation.setTitle(updatedNotation.getTitle());
            existingNotation.setDescription(updatedNotation.getDescription());
            existingNotation.setDateOfUpdate(LocalDateTime.now().toString());
            return notationRepository.save(existingNotation);
        }).orElseThrow(() -> new NotationNotFoundException(updatedNotation.getId()));
    }

    public void deleteNotation(Long id) {
        notationRepository.deleteById(id);
    }
}
