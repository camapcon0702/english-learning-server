package com.nttmk.englishlearningserver.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamSubmitDTO {
    @NotBlank
    private String examId;

    @NotEmpty
    private List<AnsweredQuestionDTO> answers;
}
