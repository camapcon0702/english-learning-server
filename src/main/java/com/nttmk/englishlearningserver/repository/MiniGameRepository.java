package com.nttmk.englishlearningserver.repository;

import com.nttmk.englishlearningserver.models.MiniGame;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MiniGameRepository extends MongoRepository<MiniGame, String> {
    boolean existsByWord(String word);
}
