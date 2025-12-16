package com.nttmk.englishlearningserver.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnsweredQuestionDTO {
    @NotBlank
    private String questionId;

    @NotBlank
    private String selectedLabel;
}
