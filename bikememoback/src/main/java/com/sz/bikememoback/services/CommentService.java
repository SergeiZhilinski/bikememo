package com.sz.bikememoback.services;

import com.sz.bikememoback.exceptions.CommentNotFoundException;
import com.sz.bikememoback.exceptions.NotationNotFoundException;
import com.sz.bikememoback.models.Comment;
import com.sz.bikememoback.models.Notation;
import com.sz.bikememoback.persistance.CommentRepository;
import com.sz.bikememoback.persistance.NotationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final NotationRepository notationRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, NotationRepository notationRepository) {
        this.commentRepository = commentRepository;
        this.notationRepository = notationRepository;
    }

    public List<Comment> getCommentsByNotationId(Long notationId) {
        return notationRepository.findById(notationId)
                .map(Notation::getComments)
                .orElseThrow(() -> new NotationNotFoundException(notationId));
    }

    public Comment getCommentById(Long notationId, Long commentId) {
        Notation notation = notationRepository.findById(notationId)
                .orElseThrow(() -> new NotationNotFoundException(notationId));
        return notation.getComments().stream()
                .filter(comment -> comment.getId().equals(commentId))
                .findFirst().orElseThrow(() -> new CommentNotFoundException(commentId));
    }

    public Comment createComment(Comment comment) {
        System.out.println("notation id :" + comment.getNotation().getId());
        Notation notation = notationRepository.findById(comment.getNotation().getId())
                .orElseThrow(() -> new NotationNotFoundException(comment.getNotation().getId()));
        comment.setOrderOfComment(notation.getComments().size());
        notation.addComment(comment);
        notationRepository.save(notation);
        return comment;
    }

    public Comment updateComment(Comment updatedComment) {
        Notation notation = notationRepository.findById(updatedComment.getNotation().getId())
                .orElseThrow(() -> new NotationNotFoundException(updatedComment.getNotation().getId()));

        return notation.getComments().stream()
                .filter(comment -> comment.getId().equals(updatedComment.getId()))
                .findFirst()
                .map(existingComment -> {
                    existingComment.setCommentTitle(updatedComment.getCommentTitle());
                    existingComment.setCommentText(updatedComment.getCommentText());
                    existingComment.setOrderOfComment(updatedComment.getOrderOfComment());
                    existingComment.setDateOfUpdate(LocalDateTime.now().toString());
                    notationRepository.save(notation);
                    return existingComment;
                }).orElseThrow(() -> new CommentNotFoundException(updatedComment.getId()));
    }

    public void deleteComment(Long notationId, String commentId) {
        Notation notation = notationRepository.findById(notationId)
                .orElseThrow(() -> new NotationNotFoundException(notationId));
        notation.getComments().removeIf(comment -> comment.getId().equals(commentId));
        notationRepository.save(notation);
    }
}
