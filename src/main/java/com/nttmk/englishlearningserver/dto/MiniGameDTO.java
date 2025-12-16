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
public class MiniGameDTO {
    @NotBlank(message = "Từ không được bỏ trống")
    private String word;

    @NotBlank(message = "Gợi ý không được bỏ trống")
    private String suggest;
}
