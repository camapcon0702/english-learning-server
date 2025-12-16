package com.nttmk.englishlearningserver.controllers;

import com.nttmk.englishlearningserver.dto.VocabularyDTO;
import com.nttmk.englishlearningserver.models.Vocabulary;
import com.nttmk.englishlearningserver.repository.VocabularyRepository;
import com.nttmk.englishlearningserver.responses.ApiResponse;
import com.nttmk.englishlearningserver.services.VocabularyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class VocabularyController {
    private final VocabularyService vocabularyService;
    private final VocabularyRepository vocabularyRepository;

    @PostMapping("/admin/vocabulary-topics/{topicId}/vocabularies")
    public ResponseEntity<ApiResponse<Vocabulary>> createVocabulary(@PathVariable String topicId, @RequestBody VocabularyDTO vocabularyDTO) {
        Vocabulary vocabulary = vocabularyService.createVocabulary(topicId, vocabularyDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(HttpStatus.CREATED.value(), "Tạo từ vựng thành công", vocabulary));
    }

    @PutMapping("/admin/vocabulary-topics/{topicId}/vocabularies/{vocabularyId}")
    public ResponseEntity<ApiResponse<Vocabulary>> updateVocabulary(@PathVariable String topicId, @PathVariable String vocabularyId, @RequestBody VocabularyDTO vocabularyDTO) {
        Vocabulary vocabulary = vocabularyService.updateVocabulary(topicId, vocabularyId, vocabularyDTO);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(HttpStatus.OK.value(), "Cập nhật từ vựng thành công", vocabulary));
    }

    @DeleteMapping("/admin/vocabulary-topics/{topicId}/vocabularies/{vocabularyId}")
    public ResponseEntity<ApiResponse<Void>>  deleteVocabulary(@PathVariable String topicId, @PathVariable String vocabularyId) {
        vocabularyService.deleteVocabulary(topicId, vocabularyId);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(HttpStatus.OK.value(), "Xoá từ vựng thành công", null));
    }

}
