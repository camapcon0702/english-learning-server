package com.nttmk.englishlearningserver.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "minigames")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MiniGame {
    @Id
    private String id;

    private String word;

    private String suggest;

    private LocalDateTime createdAt;
}
