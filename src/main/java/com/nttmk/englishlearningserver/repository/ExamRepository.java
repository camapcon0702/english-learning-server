package com.nttmk.englishlearningserver.repository;

import com.nttmk.englishlearningserver.enums.TestTypeEnums;
import com.nttmk.englishlearningserver.models.Exam;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository extends MongoRepository<Exam, String> {
    List<Exam> findAllByType(TestTypeEnums type);
}
