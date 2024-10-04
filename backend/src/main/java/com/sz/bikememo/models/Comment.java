package com.sz.bikememo.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "comments")
public class Comment {
    @Id
    private String id;
    private String title;
    private String body;
    private String userId; // ID пользователя, написавшего комментарий
    private String recordId; // ID записи, к которой относится комментарий
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

