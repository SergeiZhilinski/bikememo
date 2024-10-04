package com.sz.bikememo.repos;

import com.sz.bikememo.models.MemoRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MemoRecordRepository extends MongoRepository<MemoRecord, String> {
    List<MemoRecord> findByUserId(String userId);
}
