package com.nttmk.englishlearningserver.repository;

import com.nttmk.englishlearningserver.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String username);
    boolean existsByEmail(String email);
}
