package com.nttmk.englishlearningserver.repository;

import com.nttmk.englishlearningserver.enums.TestTypeEnums;
import com.nttmk.englishlearningserver.models.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends MongoRepository<Question, String> {
    List<Question> findByType(TestTypeEnums type);
}
