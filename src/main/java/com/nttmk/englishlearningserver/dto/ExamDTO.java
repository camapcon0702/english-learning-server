package com.nttmk.englishlearningserver.dto;

import com.nttmk.englishlearningserver.enums.TestTypeEnums;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamDTO {
    @NotBlank(message = "Tên không được để trống")
    private String title;
    private String description;

    @NotNull(message = "Loại không được để trống")
    private TestTypeEnums type;
    @Min(value = 1, message = "Thời gian làm bài tối thiểu 1 phút")
    private int duration;

    @NotEmpty(message = "Danh sách câu hỏi không được để trống")
    private List<String> questionIds;
}
