package com.nttmk.englishlearningserver.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import tools.jackson.databind.deser.bean.CreatorCandidate;

import java.time.Instant;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "users")
public class User {
    // User model
    @Id
    private String id;

    private String fullName;
    private String email;
    private String password;
    private String role;
    private String roleId;

    private Instant createdAt;
}
