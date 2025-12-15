package com.nttmk.englishlearningserver.models;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "grammars")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Grammar {
    @Id
    private String id;

    @NotBlank(message = "Tên không được để trống")
    private String name;
    private String description;

    @NotBlank(message = "Nội dung không được để trống")
    private String content;
}
