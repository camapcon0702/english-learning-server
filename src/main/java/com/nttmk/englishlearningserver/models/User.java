package com.nttmk.englishlearningserver.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@ToString
@AllArgsConstructor
@Document(collection = "users")
public class Users {
    // User model
    @Id
    private String id;

    private String fullName;
    private String email;
    private String password;
    private String role;

    private Instant createdAt;
}
