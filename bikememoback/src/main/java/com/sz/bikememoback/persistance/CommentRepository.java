package com.sz.bikememoback.persistance;

import com.sz.bikememoback.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
