package com.sz.bikememo.adapters;
import com.sz.bikememo.models.MemoRecord;
import com.sz.bikememo.repos.MemoRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/memorecords")
@RequiredArgsConstructor
public class MemoRecordController {

    private final MemoRecordRepository memoRecordRepository;

    @PostMapping
    public ResponseEntity<MemoRecord> createMemoRecord(@RequestBody MemoRecord memoRecord, @AuthenticationPrincipal Jwt jwt) {
        String userId = jwt.getSubject();
        memoRecord.setUserId(userId);
        memoRecord.setCreatedAt(LocalDateTime.now());
        memoRecord.setUpdatedAt(LocalDateTime.now());
        MemoRecord savedMemoRecord = memoRecordRepository.save(memoRecord);
        return ResponseEntity.ok(savedMemoRecord);
    }

    @GetMapping
    public List<MemoRecord> getAllMemoRecords(@AuthenticationPrincipal Jwt jwt) {
        String userId = jwt.getSubject();
        return memoRecordRepository.findByUserId(userId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemoRecord> getMemoRecordById(@PathVariable String id) {
        return memoRecordRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemoRecord> updateMemoRecord(@PathVariable String id, @RequestBody MemoRecord memoRecord, @AuthenticationPrincipal Jwt jwt) {
        String userId = jwt.getSubject();
        return (ResponseEntity<MemoRecord>) memoRecordRepository.findById(id)
                .map(existingMemoRecord -> {
                    if (!existingMemoRecord.getUserId().equals(userId)) {
                        return ResponseEntity.status(403).build();
                    }
                    existingMemoRecord.setTitle(memoRecord.getTitle());
                    existingMemoRecord.setShortDescription(memoRecord.getShortDescription());
                    existingMemoRecord.setFileUrl(memoRecord.getFileUrl());
                    existingMemoRecord.setUpdatedAt(LocalDateTime.now());
                    memoRecordRepository.save(existingMemoRecord);
                    return ResponseEntity.ok(existingMemoRecord);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMemoRecord(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
        String userId = jwt.getSubject();
        return memoRecordRepository.findById(id)
                .map(existingMemoRecord -> {
                    if (!existingMemoRecord.getUserId().equals(userId)) {
                        return ResponseEntity.status(403).build();
                    }
                    memoRecordRepository.delete(existingMemoRecord);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}

