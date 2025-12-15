package com.nttmk.englishlearningserver.repository;

import com.nttmk.englishlearningserver.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User,Integer> {
    Optional<User> findByEmail(String username);
    boolean existsByEmail(String email);
}
