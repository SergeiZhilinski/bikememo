package com.sz.bikememo.repos;

import com.sz.bikememo.models.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {
    List<Comment> findByRecordId(String recordId);
}
