package com.nttmk.englishlearningserver.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "exam_submissions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExamSubmission {
    @Id
    private String id;

    private String userId;
    private String examId;

    private List<AnsweredQuestion> answers;

    private int totalQuestions;
    private int correctAnswers;
    private double score;

    private LocalDateTime startedAt;
    private LocalDateTime submittedAt;
}
