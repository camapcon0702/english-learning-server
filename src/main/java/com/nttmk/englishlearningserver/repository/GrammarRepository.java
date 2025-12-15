package com.nttmk.englishlearningserver.repository;

import com.nttmk.englishlearningserver.models.Grammar;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrammarRepository extends MongoRepository<Grammar, String> {
}
