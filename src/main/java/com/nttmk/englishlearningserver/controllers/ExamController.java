package com.nttmk.englishlearningserver.controllers;

import com.nttmk.englishlearningserver.dto.ExamDTO;
import com.nttmk.englishlearningserver.dto.ExamRandomDTO;
import com.nttmk.englishlearningserver.dto.ExamSubmitDTO;
import com.nttmk.englishlearningserver.enums.TestTypeEnums;
import com.nttmk.englishlearningserver.responses.ApiResponse;
import com.nttmk.englishlearningserver.responses.ExamResponse;
import com.nttmk.englishlearningserver.responses.ExamResultResponse;
import com.nttmk.englishlearningserver.responses.ExamViewResponse;
import com.nttmk.englishlearningserver.services.ExamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ExamController {
    private final ExamService examService;

    @PostMapping("/admin/exams/manual")
    public ResponseEntity<ApiResponse<ExamResponse>> createManualExam(
            @Valid @RequestBody ExamDTO dto) {
        ExamResponse response = examService.createExam(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(HttpStatus.CREATED.value(), "Manual exam created", response));
    }

    @PostMapping("/admin/exams/random")
    public ResponseEntity<ApiResponse<ExamResponse>> createRandomExam(
            @Valid @RequestBody ExamRandomDTO dto) {
        ExamResponse response = examService.createExamRandom(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(HttpStatus.CREATED.value(), "Random exam created", response));
    }

    @PutMapping("/admin/exams/{id}")
    public ResponseEntity<ApiResponse<ExamResponse>> upadte(@PathVariable String id, @Valid @RequestBody ExamDTO dto) {
        ExamResponse response = examService.updateExam(id, dto);

        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Exam updated", response));
    }

    @DeleteMapping("/admin/exams/{id}")
    public ResponseEntity<ApiResponse<ExamResponse>> delete(@PathVariable String id) {
        examService.deleteExam(id);

        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Exam deleted", null));
    }

    @GetMapping("/admin/exams")
    public ResponseEntity<ApiResponse<List<ExamResponse>>> getAllExams() {
        List<ExamResponse> exams = examService.getAllExams();
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Get all exams", exams));
    }

    @GetMapping("/exams/{id}")
    public ResponseEntity<ApiResponse<ExamResponse>> getExamById(@PathVariable String id) {
        ExamResponse response = examService.getExamById(id);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Get exam", response));
    }

    @GetMapping("/exams/type")
    public ResponseEntity<ApiResponse<List<ExamResponse>>> getExamByType(@RequestParam TestTypeEnums type) {
        List<ExamResponse> exams = examService.getExamByType(type);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Get exams by type", exams));
    }

    @GetMapping("/exams/{id}/start")
    public ResponseEntity<ApiResponse<ExamViewResponse>> startExam(@PathVariable String id) {
        ExamViewResponse response = examService.startExam(id);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Get exam", response));
    }

    @PostMapping("/exams/submit")
    public ResponseEntity<ApiResponse<ExamResultResponse>> submitExam(@RequestBody @Valid ExamSubmitDTO dto) {
        ExamResultResponse response = examService.submitExam(dto);

        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Submit success", response));
    }

    @GetMapping("/exams/result")
    public ResponseEntity<ApiResponse<List<ExamResultResponse>>> getAllResult() {
        List<ExamResultResponse> responses = examService.getAllExamResult();

        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "get all result", responses));
    }
}
