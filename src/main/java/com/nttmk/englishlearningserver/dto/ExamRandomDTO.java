package com.nttmk.englishlearningserver.dto;

import com.nttmk.englishlearningserver.enums.TestTypeEnums;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExamRandomDTO {
    @NotBlank(message = "Tên không được để trống")
    private String title;
    private String description;

    @NotNull(message = "Loại không được để trống")
    private TestTypeEnums type;
    @Min(value = 1, message = "Thời gian làm bài tối thiểu 1 phút")
    private int duration;

    @Min(value = 1, message = "Số câu hỏi tối thiểu là 1")
    private int count;
}
