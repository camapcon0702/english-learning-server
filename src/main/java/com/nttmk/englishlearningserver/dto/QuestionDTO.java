package com.nttmk.englishlearningserver.dto;

import com.nttmk.englishlearningserver.enums.TestTypeEnums;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionDTO {
    @NotBlank(message = "Tiêu đề câu hỏi không được để trống")
    private String title;

    @NotNull(message = "Loại câu hỏi không được để trống")
    private TestTypeEnums type;

    @NotBlank(message = "Nội dung câu hỏi không được để trống")
    private String content;

    private String audioUrl;

    @Size(min = 2, message = "Câu hỏi phải có ít nhất 2 đáp án")
    private List<@Valid AnswerDTO> answers;
}
