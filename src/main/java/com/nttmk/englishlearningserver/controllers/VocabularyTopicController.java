package com.nttmk.englishlearningserver.controllers;

import com.nttmk.englishlearningserver.dto.VocabularyTopicDTO;
import com.nttmk.englishlearningserver.models.VocabularyTopic;
import com.nttmk.englishlearningserver.responses.ApiResponse;
import com.nttmk.englishlearningserver.services.VocabularyTopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class VocabularyTopicController {
    private final VocabularyTopicService vocabularyTopicService;

    @PostMapping("/admin/vocabulary-topics")
    public ResponseEntity<ApiResponse<VocabularyTopic>> createVocabularyTopic(@RequestBody VocabularyTopicDTO vocabularyTopicDTO) {
        VocabularyTopic vocabularyTopic = vocabularyTopicService.createVocabularyTopic(vocabularyTopicDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(HttpStatus.CREATED.value(), "Tạo Chủ đề từ vựng thành công", vocabularyTopic));
    }

    @PutMapping("/admin/vocabulary-topics/{id}")
    public ResponseEntity<ApiResponse<VocabularyTopic>> updateVocabularyTopic(@PathVariable String id, @RequestBody VocabularyTopicDTO vocabularyTopicDTO) {
        VocabularyTopic vocabularyTopic = vocabularyTopicService.updateVocabularyTopic(id, vocabularyTopicDTO);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(HttpStatus.OK.value(), "Cập nhật chủ đề từ vựng thành công", vocabularyTopic));
    }

    @DeleteMapping("/admin/vocabulary-topics/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteVocabularyTopic(@PathVariable String id) {
        vocabularyTopicService.deleteVocabularyTopicById(id);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(HttpStatus.OK.value(), "Xoá chủ đề từ vựng thành công", null));
    }

    @GetMapping("/vocabulary-topics")
    public ResponseEntity<ApiResponse<List<VocabularyTopic>>> getVocabularyTopic() {
        List<VocabularyTopic> vocabularyTopics = vocabularyTopicService.listVocabularyTopics();

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(HttpStatus.OK.value(), "Lấy từ vựng thành công", vocabularyTopics));
    }

    @GetMapping("/vocabulary-topics/{id}")
    public ResponseEntity<ApiResponse<VocabularyTopic>> getVocabularyTopicById(@PathVariable String id) {
        VocabularyTopic vocabularyTopic = vocabularyTopicService.getVocabularyTopicById(id);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(HttpStatus.OK.value(), "Lấy chủ đề thành công",  vocabularyTopic));
    }

}
