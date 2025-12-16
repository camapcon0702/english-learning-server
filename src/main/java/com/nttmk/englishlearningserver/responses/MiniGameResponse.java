package com.nttmk.englishlearningserver.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MiniGameResponse {
    private String id;
    private String word;
    private String suggest;
    private LocalDateTime createdAt;
}
