package com.sz.bikememo.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "memorecords")
public class MemoRecord {
    @Id
    private String id;
    private String title;
    private String shortDescription;
    private String fileUrl; // ссылка на файл
    private List<String> commentIds;
    private String userId; // ID пользователя, создавшего запись
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<String> tags;
}
