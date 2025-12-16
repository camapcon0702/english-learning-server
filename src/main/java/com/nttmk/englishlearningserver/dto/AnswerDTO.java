package com.nttmk.englishlearningserver.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnswerDTO {
    @Pattern(regexp = "[ABCD]", message = "Nhãn đáp án chỉ được là A, B, C hoặc D")
    private String label;

    @NotBlank(message = "Nội dung đáp án không được để trống")
    private String content;

    private boolean correct;
}
