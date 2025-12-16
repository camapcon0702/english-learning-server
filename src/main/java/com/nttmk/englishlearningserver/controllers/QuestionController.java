package com.nttmk.englishlearningserver.controllers;

import com.nttmk.englishlearningserver.dto.QuestionDTO;
import com.nttmk.englishlearningserver.responses.ApiResponse;
import com.nttmk.englishlearningserver.responses.QuestionResponse;
import com.nttmk.englishlearningserver.services.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @PostMapping("/admin/questions")
    public ResponseEntity<ApiResponse<QuestionResponse>> create(@Valid @RequestBody QuestionDTO dto) {
        QuestionResponse response = questionService.createQuestion(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(HttpStatus.CREATED.value(), "Question created", response));
    }

    @PutMapping("/admin/questions/{id}")
    public ResponseEntity<ApiResponse<QuestionResponse>> update(@PathVariable String id,@Valid @RequestBody QuestionDTO dto) {
        QuestionResponse response = questionService.updateQuestion(id, dto);

        return ResponseEntity.ok().body(new ApiResponse<>(HttpStatus.OK.value(), "Question updated", response));
    }

    @DeleteMapping("/admin/questions/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable String id) {
        questionService.deleteQuestion(id);

        return ResponseEntity.ok().body(new ApiResponse<>(HttpStatus.OK.value(), "Question deleted", null));
    }

    @GetMapping("/admin/questions")
    public ResponseEntity<ApiResponse<List<QuestionResponse>>> getAll() {
        List<QuestionResponse> response = questionService.getAllQuestions();

        return ResponseEntity.ok().body(new ApiResponse<>(HttpStatus.OK.value(), "Get question successful", response));
    }

    @GetMapping("/questions/{id}")
    public ResponseEntity<ApiResponse<QuestionResponse>> getById(@PathVariable String id) {
        QuestionResponse response = questionService.getQuestionById(id);

        return ResponseEntity.ok().body(new ApiResponse<>(HttpStatus.OK.value(), "Get question successful", response));
    }
}
