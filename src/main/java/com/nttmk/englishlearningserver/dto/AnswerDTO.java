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
public class AnswerDTO {
    @NotBlank(message = "Nhãn đáp án (A, B, C, D) không được để trống")
    private String label;

    @NotBlank(message = "Nội dung đáp án không được để trống")
    private String content;

    private boolean correct;
}
